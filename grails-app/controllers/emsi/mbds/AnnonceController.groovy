package emsi.mbds

import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException

import static java.util.UUID.*
import static org.springframework.http.HttpStatus.*

@Secured(['ROLE_ADMIN', 'ROLE_MODO'])
class AnnonceController {

    AnnonceService annonceService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def liste = Annonce.createCriteria().list(params){
            if ( params.motCle ) {
                ilike("title", "%${params.motCle}%")
            }
        }
        model: [annonceList:liste, annonceCount: annonceService.count()]
    }

    def show(Long id) {
        respond annonceService.get(id)
    }

    def create() {
        respond new Annonce(params)
    }


    def save(Annonce annonce) {
        if (annonce == null) {
            notFound()
            return
        }

        try {
            def illustration = new Illustration()
            def illustrations = new ArrayList<>()
            def uuid = UUID.randomUUID()
            def f = request.getFile('filename')
            f.transferTo( new File(grailsApplication.config.images.chemin + uuid.toString() + ".jpg"))
            illustration.setFilename(uuid.toString()  + ".jpg")
            illustration.setAnnonce(annonce)
            illustrations.add(illustration)
            annonce.setIllustrations(illustrations)
            annonceService.save(annonce)
        } catch (ValidationException e) {
            respond annonce.errors, view: 'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'annonce.label', default: 'Annonce'), annonce.id])
                redirect annonce
            }
            '*' { respond annonce, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond annonceService.get(id)
    }

    def update(Annonce annonce) {
        if (annonce == null) {
            notFound()
            return
        }

        try  {
            annonceService.save(annonce)
        } catch (ValidationException e) {
            respond annonce.errors, view: 'edit'
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'annonce.label', default: 'Annonce'), annonce.id])
                redirect annonce
            }
            '*' { respond annonce, [status: OK] }
        }
    }


    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        annonceService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'annonce.label', default: 'Annonce'), id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'annonce.label', default: 'Annonce'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
