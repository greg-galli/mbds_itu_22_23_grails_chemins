<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'pathway.label', default: 'Pathway')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<div id="content" role="main">
    <div class="container">
        <section class="row">
            <a href="#list-pathway" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                          default="Skip to content&hellip;"/></a>

            <div class="nav" role="navigation">
                <ul>
                    <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                    <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                                          args="[entityName]"/></g:link></li>
                </ul>
            </div>
        </section>
        <section class="row">
            <div id="list-pathway" class="col-12 content scaffold-list" role="main">
                <h1><g:message code="default.list.label" args="[entityName]"/></h1>
                <g:if test="${flash.message}">
                    <div class="message" role="status">${flash.message}</div>
                </g:if>
                <table>
                    <thead>
                    <tr>

                        <th class="sortable"><a href="/pathway/index?sort=title&amp;max=10&amp;order=asc">Title</a></th>

                        <th class="sortable"><a
                                href="/pathway/index?sort=description&amp;max=10&amp;order=asc">Description</a></th>

                        <th class="sortable"><a href="/pathway/index?sort=pois&amp;max=10&amp;order=asc">Pois</a></th>

                        <th class="sortable"><a
                                href="/pathway/index?sort=illustrations&amp;max=10&amp;order=asc">Illustrations</a></th>

                    </tr>
                    </thead>
                    <tbody>
                    <g:each in="${projet_itu_mbds_22_23.Pathway.list()}" var="pathway">
                        <tr class="even">
                            <td><a href="/pathway/show/1">${pathway.title}</a></td>
                            <td>${pathway.description}</td>
                            <td><ul><li><a href="/poi/show/1">projet_itu_mbds_22_23.Poi : 1</a></li><li><a
                                    href="/poi/show/2">projet_itu_mbds_22_23.Poi : 2</a></li><li><a
                                    href="/poi/show/3">projet_itu_mbds_22_23.Poi : 3</a></li></ul></td>

                            <td>
                                <g:each in="${pathway.illustrations}" var="illu">
                                    <img src="${grailsApplication.config.config.illustrations.baseUrl+illu.filename}" />
                                </g:each>
                            </td>

                        </tr>
                    </g:each>
                    </tbody>
                </table>

                <g:if test="${pathwayCount > params.int('max')}">
                    <div class="pagination">
                        <g:paginate total="${pathwayCount ?: 0}"/>
                    </div>
                </g:if>
            </div>
        </section>
    </div>
</div>
</body>
</html>