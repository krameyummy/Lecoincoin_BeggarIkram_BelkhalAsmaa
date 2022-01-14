<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'annonce.label', default: 'Annonce')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div id="list-annonce" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <table class="table" style="width:100%">
                <thead>
                <tr>
                    <th>Titre</th>
                    <th>Description</th>
                    <th>Date de publication</th>
                    <th>Illustrations</th>
                    <th>Publié par</th>
                </tr>
                </thead>
                <tbody>
                <g:each in="${annonceList}">
                    <tr>
                        <td><g:link controller="annonce" action="show" id="${it.id}">${it.title}</g:link></td>
                        <td>${it.description}</td>
                        <td>${it.dateCreated}</td>
                        <td>
                            <g:each in="${it.illustrations}" var="ill">
                                <g:img file="${ill.filename}" />
                            </g:each>
                        </td>
                        <td><g:link controller="user" action="show" id="${it.author.id}">${it.author.username}</g:link></td>
                    </tr>
                </g:each>
                </tbody>
            </table>

            <div class="pagination">
                <g:paginate total="${annonceCount ?: 0}" params="${params}" />
            </div>
        </div>
    </body>
</html>