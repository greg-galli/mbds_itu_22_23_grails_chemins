<%@ page import="projet_itu_mbds_22_23.User" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'pathway.label', default: 'Pathway')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>
</head>

<body>
<div id="content" role="main">
    <div class="container">
        <section class="row">
            <a href="#create-pathway" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                            default="Skip to content&hellip;"/></a>

            <div class="nav" role="navigation">
                <ul>
                    <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                    <li><g:link class="list" action="index"><g:message code="default.list.label"
                                                                       args="[entityName]"/></g:link></li>
                </ul>
            </div>
        </section>
        <section class="row">
            <div id="create-pathway" class="col-12 content scaffold-create" role="main">
                <h1><g:message code="default.create.label" args="[entityName]"/></h1>
                <g:if test="${flash.message}">
                    <div class="message" role="status">${flash.message}</div>
                </g:if>
                <g:hasErrors bean="${this.pathway}">
                    <ul class="errors" role="alert">
                        <g:eachError bean="${this.pathway}" var="error">
                            <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                                    error="${error}"/></li>
                        </g:eachError>
                    </ul>
                </g:hasErrors>
                <g:form resource="${this.pathway}" method="POST" enctype="multipart/form-data">
                    <fieldset class="form">
                        <div class="fieldcontain required">
                            <label for="title">Title
                                <span class="required-indicator">*</span>
                            </label><input type="text" name="title" value="" required="" maxlength="55" id="title">
                        </div>

                        <div class="fieldcontain required">
                            <label for="description">Description
                                <span class="required-indicator">*</span>
                            </label><input type="text" name="description" value="" required="" id="description">
                        </div>

                        <div class="fieldcontain">
                            <label for="moderateurs">Moderateurs</label>
                            <g:select name="moderateurs" from="${projet_itu_mbds_22_23.User.list()}" optionKey="id" optionValue="username" multiple="true"/>
                        </div>

                        <div class="fieldcontain">
                            <label for="illustrations">Illustrations</label>
                            <input type="file" name="illu" />
                        </div>
                    </fieldset>
                    <fieldset class="buttons">
                        <g:submitButton name="create" class="save"
                                        value="${message(code: 'default.button.create.label', default: 'Create')}"/>
                    </fieldset>
                </g:form>
            </div>
        </section>
    </div>
</div>
</body>
</html>
