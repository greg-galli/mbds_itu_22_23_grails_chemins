package projet_itu_mbds_22_23

class Poi {

    String title
    String description
    Date dateCreated
    Float lat
    Float lng

    static hasMany = [illustrations: Illustration]

    static belongsTo = [pathway: Pathway]

    static constraints = {
        title nullable: false, blank: false, size: 5..55
        description nullable: false, blank: false
        lat nullable: true
        lng nullable: true
    }
}
