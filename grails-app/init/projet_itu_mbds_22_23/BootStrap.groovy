package projet_itu_mbds_22_23

import java.nio.file.Path

class BootStrap {

    def init = { servletContext ->
        def userRole = new Role(authority: "USER_ROLE").save()
        // Création des users
        ["Alice", "Bob"].each {
            String username ->
                User.withTransaction {
                    def userInstance = new User(username: username, password: "password").save()
                    UserRole.create(userInstance, userRole, true)

                    // Création des parcours
                    ["Par ici", "Par là", "La bas", "Encore plus loin", "Perdu"].each {
                        String nomPacours ->
                                def parcoursInstance = new Pathway(title: nomPacours, description: "Une belle description")
                                (1..2).each {
                                    parcoursInstance.addToIllustrations(new Illustration(filename: "grails.svg"))
                                }
                                // Création des POIs
                                ["Premier", "Second", "Troisième"].each {
                                    String poiName ->
                                        def poiInstance = new Poi(title: nomPacours + " " + poiName, description: "Tralala")
                                        (1..5).each {
                                            poiInstance.addToIllustrations(new Illustration(filename: "filename$nomPacours$poiName$it"))
                                        }
                                        parcoursInstance.addToPois(poiInstance)
                                }
                            userInstance.addToParcours(parcoursInstance)
                            userInstance.save(flush: true, failOnError: true)
                    }
                }
        }

    }
    def destroy = {
    }
}
