package projet_itu_mbds_22_23

import grails.gorm.services.Service

@Service(Pathway)
interface PathwayService {

    Pathway get(Serializable id)

    List<Pathway> list(Map args)

    Long count()

    void delete(Serializable id)

    Pathway save(Pathway pathway)

}