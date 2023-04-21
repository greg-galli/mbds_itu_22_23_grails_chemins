package projet_itu_mbds_22_23

import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import org.springframework.web.multipart.MultipartFile

import static org.springframework.http.HttpStatus.*

@Secured('isFullyAuthenticated()')
class PathwayController {

    PathwayService pathwayService
    SpringSecurityService springSecurityService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond pathwayService.list(params), model:[pathwayCount: pathwayService.count()]
    }

    def show(Long id) {
        respond pathwayService.get(id)
    }

    def create() {
        respond new Pathway(params)
    }

    def save(Pathway pathway) {
        if (pathway == null) {
            notFound()
            return
        }

        MultipartFile file = request.getFile('illu')
        file.transferTo(new File(grailsApplication.config.config.illustrations.basePath+"toto.png"))
        pathway.addToIllustrations(new Illustration(filename: "toto.png"))
        pathway.author = ((User)springSecurityService.getCurrentUser())


        try {
            pathwayService.save(pathway)
        } catch (ValidationException e) {
            respond pathway.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'pathway.label', default: 'Pathway'), pathway.id])
                redirect pathway
            }
            '*' { respond pathway, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond pathwayService.get(id)
    }

    def update(Pathway pathway) {
        if (pathway == null) {
            notFound()
            return
        }

        try {
            pathwayService.save(pathway)
        } catch (ValidationException e) {
            respond pathway.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'pathway.label', default: 'Pathway'), pathway.id])
                redirect pathway
            }
            '*'{ respond pathway, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        pathwayService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'pathway.label', default: 'Pathway'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'pathway.label', default: 'Pathway'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
