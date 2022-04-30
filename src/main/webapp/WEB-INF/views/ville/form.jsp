<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Liste des villes de France</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://kit.fontawesome.com/83345b9afd.js" crossorigin="anonymous"></script>
</head>
<body>

    <header class="d-flex flex-wrap justify-content-center py-3 mb-4 border-bottom">
        <a href="/" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-dark text-decoration-none">
            <svg class="bi me-2" width="40" height="32"><use xlink:href="#bootstrap"></use></svg>
            <span class="fs-4">TWIC</span>
        </a>
    </header>


    <section class="container">
        <div class="card">
            <div class="card-header">
                <h5 class="card-title"><c:out value="${mode}"></c:out></h5>
            </div>
            <div class="card-body">
                <c:choose>
                    <c:when test="${success}">
                        <div class="alert alert-success">
                            <c:out value="${message}"></c:out>
                        </div>
                    </c:when>
                    <c:when test="${!success}">
                        <div class="alert alert-error">
                            <c:out value="${message}"></c:out>
                        </div>
                    </c:when>
                </c:choose>
                <form action="${action}" method="post">
                    <div class="mb-3">
                        <label class="label" for="cityName">Nom de la ville</label>
                        <input class="form-control" type="text" id="cityName" name="cityName" placeholder="Paris, New York, Dublin, Belfast, ..." value="${ville.name}">
                    </div>
                    <div class="mb-3">
                        <label class="label" for="postalCode">Code postal</label>
                        <input class="form-control" type="text" id="postalCode" name="postalCode" placeholder="72230" value="${ville.postalCode}">
                    </div>
                    <div class="mb-3">
                        <label class="label" for="label">Libellé</label>
                        <input class="form-control" type="text" id="label" name="label" placeholder="" value="${ville.label}">
                    </div>
                    <div class="mb-3">
                        <label class="label" for="inseeCode">Code INSEE</label>
                        <input class="form-control" type="text" id="inseeCode" name="inseeCode" placeholder="Code INSEE" value="${ville.inseeCode}">
                    </div>
                    <div class="mb-3">
                        <label class="label" for="latitude">Latitude</label>
                        <input class="form-control" type="text" id="latitude" name="latitude" value="${ville.lat}">
                    </div>

                    <div class="mb-3">
                        <label class="label" for="longitude">Longitude</label>
                        <input class="form-control" type="text" id="longitude" name="longitude" value="${ville.lon}">
                    </div>

                    <div class="mb-3">
                        <label class="label" for="line5">Ligne 5</label>
                        <input class="form-control" type="text" id="line5" name="line5" value="${ville.line5}">
                    </div>

                    <div class="mb-3">
                        <button class="btn btn-success" type="submit"><c:out value="${mode}"></c:out></button>
                    </div>
                </form>
            </div>
        </div>
    </section>
</body>
</html>
