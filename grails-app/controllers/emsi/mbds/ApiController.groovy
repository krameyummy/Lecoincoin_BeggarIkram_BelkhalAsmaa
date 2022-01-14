package emsi.mbds

import grails.converters.JSON
import grails.converters.XML
import grails.plugin.springsecurity.annotation.Secured

class ApiController {

    /**
     * GET (Done) / PUT (Done)  / PATCH / DELETE (Done)
     * Pour une note max : Gérer la notion de role en plus de l'utilisateur
     */

    UserService userService
    AnnonceService annonceService

    @Secured('ROLE_ADMIN')
    def user() {
        switch (request.getMethod()) {
            case "GET":
                if (!params.id)
                    return response.status = 400
                def userInstance = User.get(params.id)
                if (!userInstance)
                    return response.status = 404
                response.withFormat {
                    xml { render userInstance as XML }
                    json { render userInstance as JSON }
                }
                break;

            case "PUT":
                if (!params.id)
                    return response.status = 400
                def userInstance = User.get(params.id)
                if (!userInstance)
                    return response.status = 404
                def username = request.JSON.username
                userInstance.username = username
                def password = request.JSON.password
                userInstance.password = password
                userService.save(userInstance)
                response.withFormat {
                    xml { render userInstance as XML }
                    json { render userInstance as JSON }
                }
                break;

            case "PATCH":
                // Mise à jour partielle d'un user
                if (!params.id)
                    return response.status = 400
                def userInstance = User.get(params.id)
                if (!userInstance)
                    return response.status = 404
                if(request.JSON.username != null) {
                    def username = request.JSON.username
                    userInstance.username = username
                }
                if(request.JSON.password != null) {
                    println(request.JSON.password)
                    def password = request.JSON.password
                    userInstance.password = password
                }
                userService.save(userInstance)
                response.withFormat {
                    xml { render userInstance as XML }
                    json { render userInstance as JSON }
                }
                break;

            case "DELETE":
                // Supprimer un user
                if (!params.id)
                    return response.status = 400
                def userInstance = User.get(params.id)
                if (!userInstance)
                    return response.status = 404
                UserRole.where { user == userInstance}.deleteAll()
                userService.delete(params.id)
                return response.status = 200
                break;
            default:
                return response.status = 405
                break;
        }
        return response.status = 406
    }

    /**
     * POST / GET
     */

    @Secured('ROLE_ADMIN')
    def users() {
        switch (request.getMethod()) {
            case "POST":
                def userInstance = new User()
                userInstance.username = request.JSON.username
                userInstance.password = request.JSON.password
                UserRole.create(userInstance, Role.get(request.JSON.role),true)
                 if (!userService.save(userInstance))
                     return response.status = 400
                 response.withFormat{
                     xml { render userInstance as XML }
                     json { render userInstance as JSON }
                 }
                break;
            case "GET":
                def userList = userService.list(null)
                if(!userList)
                    return response.status = 404
                response.withFormat {
                    xml { render userList as XML }
                    json { render userList as JSON }
                }
                break
            default:
                return response.status = 405
                break;
        }
        return response.status = 406
    }
    /**
     * GET / PUT / PATCH / DELETE
     */
    @Secured('ROLE_ADMIN')
    def annonce() {
        switch (request.getMethod()) {
            case "GET":
                if (!params.id)
                    return response.status = 400
                def annonce = Annonce.get(params.id)
                if (!annonce)
                    return response.status = 404
                response.withFormat {
                    xml { render annonce as XML }
                    json { render annonce as JSON }
                }
                break;
            case "PUT":
                if (!params.id)
                    return response.status = 400
                def annonce = Annonce.get(params.id)
                if (!annonce)
                    return response.status = 404
                if(request.JSON.title && request.JSON.description && request.JSON.price){
                    annonce.title = request.JSON.title
                    annonce.description = request.JSON.description
                    annonce.price = request.JSON.price
                }
                annonceService.save(annonce)
                response.withFormat {
                    xml { render annonce as XML }
                    json { render annonce as JSON }
                }
                break;
            case "PATCH":
                if (!params.id)
                    return response.status = 400
                def annonce = Annonce.get(params.id)
                if (!annonce)
                    return response.status = 404
                if(request.JSON.title && request.JSON.description){
                    annonce.setTitle(request.JSON.title)
                    annonce.setDescription(request.JSON.description)
                    annonceService.save(annonce)
                }
                response.withFormat {
                    xml { render annonce as XML }
                    json { render annonce as JSON }
                }
                break;
            case "DELETE":
                if (!params.id)
                    return response.status = 400
                def annonce = Annonce.get(params.id)
                if (!annonce)
                    return response.status = 404
                annonceService.delete(annonce.getId())
                return response.status = 200
                break;
            default:
                return response.status = 405
                break;
        }
        return response.status = 406
    }


    /**
     * POST / GET
     * Pour une note maximale : gérer la notion d'illustration
     */
    @Secured('ROLE_ADMIN')
    def annonces() {
        switch (request.getMethod()) {
            case "POST":
                def annonce = new Annonce()
                if(request.JSON.title != null && request.JSON.description != null && request.JSON.author != null && request.JSON.ref != null){
                    annonce.title = request.JSON.title
                    annonce.description = request.JSON.description
                    annonce.author = User.findByUsername(request.JSON.author)
                    annonce.ref = request.JSON.ref
                }
                if (!annonceService.save(annonce))
                    return response.status = 400
                response.withFormat{
                    xml { render annonce as XML }
                    json { render annonce as JSON }
                }
                break;
            case "GET":
                def annonceList = annonceService.list(null)
                if(!annonceList)
                    return response.status = 404
                response.withFormat {
                    xml { render annonceList as XML }
                    json { render annonceList as JSON }
                }
                break
            default:
                return response.status = 405
                break;
        }
        return response.status = 406
    }
}

