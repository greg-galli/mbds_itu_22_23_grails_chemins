package projet_itu_mbds_22_23

class Pathway {

    String title
    String description
    Date dateCreated
    Date lastUpdated
    List pois


    static hasMany = [pois: Poi, illustrations: Illustration]

    static constraints = {
        title nullable: false, blank: false, size: 5..55
        description nullable: false, blank: false
    }
}
