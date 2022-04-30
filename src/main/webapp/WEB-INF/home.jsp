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

    <main class="container">
        <h1>Liste des villes de France</h1>

        <div class="card">
            <div class="card-header">
                <h5 class="card-title">Distance entre deux villes</h5>
            </div>
            <div class="card-body">
                <c:if test="${from != null && to != null && distance != null}">
                    <c:out value="Distance : ${distance}m"></c:out>
                </c:if>
                
                <form method="post">
                    <div class="mb-3">
                        <select class="form-select" aria-label="Choisir une ville de départ" name="fromInseeCode">
                            <option selected>Choisir une ville de départ</option>
                            <c:forEach items="${villes}" var="ville">
                                <option value="${ville.inseeCode}"><c:out value="${ville.name}"></c:out></option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="mb-3">
                        <select class="form-select" aria-label="Choisir une ville d'arrivée" name="toInseeCode">
                            <option selected>Choisir une ville d'arrivée</option>
                            <c:forEach items="${villes}" var="ville">
                                <option value="${ville.inseeCode}"><c:out value="${ville.name}"></c:out></option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="mb-3">
                        <input type="submit" class="btn btn-success" value="Calculer">
                        <a href="/ville/create" class="btn btn-primary float-end">Créer une ville</a>
                    </div>
                </form>
            </div>
        </div>

        <div class="card">
            <div class="card-header">
                <h5 class="card-title">Liste des villes</h5>
            </div>
            <div class="card-body">
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Nom de la ville</th>
                        <th scope="col">Code Postal</th>
                        <th scope="col">Latitude</th>
                        <th scope="col">Longitude</th>
                        <th scope="col">Outils</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${ pagination }" var="ville" varStatus="loop">
                        <tr>
                            <th scope="row">${loop.index + 1}</th>
                            <td>${ville.name}</td>
                            <td>${ville.postalCode}</td>
                            <td>${ville.lat}</td>
                            <td>${ville.lon}</td>
                            <td>
                                <div class="btn-group">
                                    <a href="/ville/update?inseeCode=${ville.inseeCode}"><i class="fa-solid fa-pen"></i></a>
                                    <a href="http://www.openstreetmap.org/?lat=${ville.lat}&lon=${ville.lon}&zoom=14&layers=M" class="btn btn-sm"><i class="fa-solid fa-map"></i></a>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="card-footer">
                <nav aria-label="Page navigation example">
                    <ul class="pagination">
                        <li class="page-item"><a class="page-link" href="/?page=${sessionScope.page - 1}">Précédent</a></li>
                        <li class="page-item"><a class="page-link" href="/?page=${sessionScope.page}"><c:out value="${sessionScope.page + 1}"></c:out></a></li>
                        <li class="page-item"><a class="page-link" href="/?page=${sessionScope.page + 1}"><c:out value="${sessionScope.page + 2}"></c:out></a></li>
                        <li class="page-item"><a class="page-link" href="/?page=${sessionScope.page + 2}"><c:out value="${sessionScope.page + 3}"></c:out></a></li>
                        <li class="page-item"><a class="page-link" href="/?page=${sessionScope.page + 1}">Suivant</a></li>
                    </ul>
                </nav>
            </div>
        </div>
    </main>

</body>
</html>
