package projet_itu_mbds_22_23

import java.nio.file.Path

class BootStrap {

    def init = { servletContext ->
        def userRole = new Role(authority: "USER_ROLE").save()
        ["Alice", "Bob"].each {
            String username ->
                User.withTransaction {
                    def userInstance = new User(username: username, password: "password").save()
                    UserRole.create(userInstance, userRole, true)
                }
        }
        ["Par ici", "Par là", "La bas", "Encore plus loin", "Perdu"].each {
            String nomPacours ->
                Pathway.withTransaction {
                    def parcoursInstance = new Pathway(title: nomPacours, description: "Une belle description").save()
                    (1..2).each {
                        parcoursInstance.addToIllustrations(new Illustration(filename: "filename$nomPacours$it"))
                    }
                    ["Premier", "Second", "Troisième"].each {
                        String poiName ->
                            def poiInstance = new Poi(title: nomPacours + " " + poiName, description: "Tralala")
                            (1..5).each {
                                poiInstance.addToIllustrations(new Illustration(filename: "filename$nomPacours$poiName$it"))
                            }
                            parcoursInstance.addToPois(poiInstance)
                    }
                    parcoursInstance.save(flush: true, failOnError: true)
                }
        }
    }
    def destroy = {
    }
}
