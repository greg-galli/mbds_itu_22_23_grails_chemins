package projet_itu_mbds_22_23

import grails.converters.JSON
import grails.converters.XML
import grails.plugin.springsecurity.annotation.Secured

@Secured('isFullyAuthenticated()')
class ApiController {

    /**
     * Ressource Singleton
     * GET, PUT, PATCH, DELETE
     */
    def poi() {
        switch (request.getMethod()) {
            case "GET":
                // On vérifie que l'id soit bien fourni
                if (!params.id)
                    return response.status = 400
                def poiInstance = Poi.get(params.id)
                // On vérifie que l'instance correspondante à l'id soit bien définie
                if (!poiInstance)
                    return response.status = 404
                // On sérialise le contenu dans le format demandé
                serializeThis(poiInstance, request.getHeader("Accept"))
                break
            case "PUT":
                break
            case "PATCH":
                break
            case "DELETE":
                break
            default:
                break
        }
    }

    /**
     * Ressource Collection
     * GET, POST
     */
    def pois() {

    }

    def parcour() {

    }

    def parcours() {

    }

    def serializeThis(Object object, String accept) {
        switch (accept) {
            case "text/json":
            case "application/json":
            case "json":
                render object as JSON
                break
            case "text/xml":
            case "application/xml":
            case "xml":
                render object as XML
                break
            default:
                return response.status = 406
                break
        }
    }
}
