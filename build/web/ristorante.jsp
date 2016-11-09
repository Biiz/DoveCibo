<%-- 
    Document   : ristorante
    Created on : 22-lug-2016, 10.59.10
    Author     : IO-PC
--%>

<%@page import="DoveCiboPK.Review"%>
<%@page import="jdk.nashorn.internal.runtime.RewriteException"%>
<%@page import="DoveCiboPK.Cusine"%>
<%@page import="DoveCiboPK.Restaurant"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DoveCibo</title>

        <style>
            .btn-like {
                padding: 14px 24px;
                border: 0 none;
                font-weight: 700;
                letter-spacing: 1px;
                text-transform: uppercase;
            }

            .btn-like:focus, .btn-like:active:focus, .btn-like.active:focus {
                outline: 0 none;
            }

            .btn-default {
                background: rgba(255, 255, 255, 0);
                color: none;
            }

            .btn-default:hover, .btn-default:focus, .btn-default:active, .btn-default.active, .open > .dropdown-toggle.btn-default {
                color: #0099ff;
                background-color: rgba(255, 255, 255, 0);
            }

            .btn-default:active, .btn-default.active {
                background: rgba(255, 255, 255, 0);
                box-shadow: white;
            }

            #myCarousel img {
                min-width: 600px;
                max-width: 600px;
                height: auto;
                max-height: 400px;
                margin: auto;
            }

            body {
                background-image: url("img/img (7)b.jpg");
                background-repeat: no-repeat;
                background-attachment: fixed;
                background-size: cover;
            }
            .colonna2{
                background-color: rgba(255, 255, 255, 0.80);
                box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.5), 0 6px 20px 0 rgba(0, 0, 0, 0.7);
                border-radius: 5px;
            }
        </style>
    </head>
    <body>
        <%@ include file="navBar.jsp" %>
        <%  Restaurant R = (Restaurant) request.getAttribute("ristorante");%>
        <div class="modal-dialog modal-lg">
            <div class="modal-content colonna2" >
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-4">
                            <div id="tagline">
                                <p style="color: black; font-size: 28px"><b><%=R.getName()%></b></p>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <ul>
                                        <li><span class="glyphicon glyphicon-map-marker" aria-hidden="true"></span> <b><%=R.getCordinate().getAdrers()%></b></li>
                                        <li><span class="glyphicon glyphicon-euro" aria-hidden="true"></span> <b><%=R.getPrice_range().getMin_value()%> - <%=R.getPrice_range().getMax_value()%></b></li>
                                        <li><span class="glyphicon glyphicon-star" aria-hidden="true"></span> <b><%=R.getGlobal_value()%></b></li>
                                        <li><span class="glyphicon glyphicon-link" aria-hidden="true"></span> <b><%=R.getWeb_site_url()%></b></li>
                                        <li><span class="glyphicon glyphicon-cutlery" aria-hidden="true"></span> <b>

                                                <% for (Cusine c : R.getCusines()) {%>
                                                <%= c.getName()%>
                                                <% }%>

                                            </b></li>
                                        <li><span class="glyphicon glyphicon-qrcode" aria-hidden="true"></span> <b>QRcode</b></li>

                                    </ul>

                                </div>
                            </div>
                        </div>
                        <div class="col-md-8">

                            <div id="myCarousel" class="carousel slide" data-ride="carousel">
                                <!-- Indicators -->
                                <ol class="carousel-indicators">
                                    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                                    <li data-target="#myCarousel" data-slide-to="1"></li>
                                    <li data-target="#myCarousel" data-slide-to="2"></li>
                                    <li data-target="#myCarousel" data-slide-to="3"></li>
                                    <li data-target="#myCarousel" data-slide-to="4"></li>
                                    <li data-target="#myCarousel" data-slide-to="5"></li>
                                    <li data-target="#myCarousel" data-slide-to="6"></li>

                                </ol>

                                <!-- Wrapper for slides -->
                                <div class="carousel-inner" role="listbox">
                                    <div class="item active">
                                        <img src="img/img (1).jpeg" alt="">
                                    </div>

                                    <div class="item">
                                        <img src="img/img (1).jpg" alt="">
                                    </div>

                                    <div class="item">
                                        <img src="img/img (2).jpg" alt="">
                                    </div>
                                    <div class="item">
                                        <img src="img/img (3).jpg" alt="">
                                    </div>
                                    <div class="item">
                                        <img src="img/img (4).jpg" alt="">
                                    </div>
                                    <div class="item">
                                        <img src="img/img (5).jpg" alt="">
                                    </div>
                                    <div class="item">
                                        <img src="img/img (6).jpg" alt="">
                                    </div>
                                </div>

                                <!-- Left and right controls -->
                                <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
                                    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                                    <span class="sr-only">Previous</span>
                                </a>
                                <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
                                    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                                    <span class="sr-only">Next</span>
                                </a>
                            </div>
                        </div>                        
                    </div>

                    <hr align=”left” size=”1″ width=”300″ style="border-top-color: grey;" noshade>

                    <div class="row">
                        <div class="col-md-12">
                            <p style="color: #333333; font-size: 20px; margin-bottom: 0px; border-bottom: 0px;"> <%= R.getDescription()%></p>                                    

                        </div> 
                    </div>

                </div>
            </div>   
        </div>
        <!-- AGGIUNGI CONTROOL COKY -->
        <div class="modal-dialog modal-lg" >
            <div class="modal-content colonna2">
                <div class="modal-body">
                    <div class="row">

                        <div class="col-md-12 text-center">
                            <label class="btn btn-primary btn-lg"><span class="glyphicon glyphicon-picture"></span> Carica foto<input type="file" accept="image/*" style="display: none;"></label>

                            <a href="#diversi" class="btn btn-info btn-lg" data-toggle="collapse" ><span class="glyphicon glyphicon-comment"></span> Scrivi una recensione</a>

                            <button type="button" class="btn btn-danger btn-lg"><span class="glyphicon glyphicon-flag" aria-hidden="true"></span> Reclama</button> 
                        </div> 
                    </div>

                    <div id="diversi" class="collapse">
                        <div class="row">

                            <form method="POST" action="ServletAggiungiCommento" >
                                <input type="hidden" name="ristorante" value="<%=R.getId()%>">
                                <div class="col-md-12">    
                                    <hr align=”left” size=”1″ width=”300″ style="border-top-color: grey;" noshade>

                                    <div class="row">
                                        <div class="col-md-12">
                                            <div id="tagline">
                                                <p style="color: black; font-size: 20px; padding-top: 15px;"><b>Inserisci commento</b></p>
                                            </div>
                                        </div>

                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <p>Titolo commento</p>
                                                <textarea class="form-control" rows="1" id="comment" name="name"></textarea>
                                                <p>Descrizione commento</p>
                                                <textarea class="form-control" rows="5" id="comment" name="description"></textarea>
                                            </div>
                                        </div>

                                    </div>

                                    <div class="row">
                                        <div class="col-md-12">
                                            <div id="tagline">
                                                <p style="color: black; font-size: 20px; padding-top: 15px;"><b>Valutazione generale</b></p>
                                            </div>

                                            <fieldset class="rating">
                                                <input type="radio" id="star1" name="global_value" value="1" />
                                                <label for="star1"> 1 &ensp;</label>
                                                <input type="radio" id="star2" name="global_value" value="2" />
                                                <label for="star2"> 2 &ensp;</label>
                                                <input type="radio" id="star3" name="global_value" checked="checked" value="3" />
                                                <label for="star3"> 3 &ensp;</label>
                                                <input type="radio" id="star4" name="global_value" value="4" />
                                                <label for="star4"> 4 &ensp;</label>
                                                <input type="radio" id="star5" name="global_value" value="5" />
                                                <label for="star5"> 5 &ensp;stelle</label>
                                            </fieldset>

                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-12">
                                            <div id="tagline">
                                                <p style="color: black; font-size: 20px; padding-top: 15px;"><b>Cibo</b></p>
                                            </div>

                                            <fieldset class="rating">
                                                <input type="radio" id="star1" name="food" value="1" />
                                                <label for="star1"> 1 &ensp;</label>
                                                <input type="radio" id="star2" name="food" value="2" />
                                                <label for="star2"> 2 &ensp;</label>
                                                <input type="radio" id="star3" name="food" checked="checked" value="3" />
                                                <label for="star3"> 3 &ensp;</label>
                                                <input type="radio" id="star4" name="food" value="4" />
                                                <label for="star4"> 4 &ensp;</label>
                                                <input type="radio" id="star5" name="food" value="5" />
                                                <label for="star5"> 5 &ensp;stelle</label>
                                            </fieldset>

                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-12">
                                            <div id="tagline">
                                                <p style="color: black; font-size: 20px; padding-top: 15px;"><b>Servizio</b></p>
                                            </div>

                                            <fieldset class="rating">
                                                <input type="radio" id="star1" name="service" value="1" />
                                                <label for="star1"> 1 &ensp;</label>
                                                <input type="radio" id="star2" name="service" value="2" />
                                                <label for="star2"> 2 &ensp;</label>
                                                <input type="radio" id="star3" name="service" checked="checked" value="3" />
                                                <label for="star3"> 3 &ensp;</label>
                                                <input type="radio" id="star4" name="service" value="4" />
                                                <label for="star4"> 4 &ensp;</label>
                                                <input type="radio" id="star5" name="service" value="5" />
                                                <label for="star5"> 5 &ensp;stelle </label>
                                            </fieldset>

                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-12">
                                            <div id="tagline">
                                                <p style="color: black; font-size: 20px; padding-top: 15px;"><b>Qualità/Prezzo</b></p>
                                            </div>

                                            <fieldset class="rating">
                                                <input type="radio" id="star1" name="value_for_money" value="1" />
                                                <label for="star1"> 1 &ensp;</label>
                                                <input type="radio" id="star2" name="value_for_money" value="2" />
                                                <label for="star2"> 2 &ensp;</label>
                                                <input type="radio" id="star3" name="value_for_money" checked="checked" value="3" />
                                                <label for="star3"> 3 &ensp;</label>
                                                <input type="radio" id="star4" name="value_for_money" value="4" />
                                                <label for="star4"> 4 &ensp;</label>
                                                <input type="radio" id="star5" name="value_for_money" value="5" />
                                                <label for="star5"> 5 &ensp;stelle</label>
                                            </fieldset>

                                        </div>
                                    </div>           

                                    <div class="row">
                                        <div class="col-md-12">
                                            <div id="tagline">
                                                <p style="color: black; font-size: 20px; padding-top: 15px;"><b>Atmosfera:</b></p>
                                            </div>

                                            <fieldset class="rating">
                                                <input type="radio" id="star1" name="atmospere" value="1" />
                                                <label for="star1"> 1 &ensp;</label>
                                                <input type="radio" id="star2" name="atmospere" value="2" />
                                                <label for="star2"> 2 &ensp;</label>
                                                <input type="radio" id="star3" name="atmospere" checked="checked" value="3" />
                                                <label for="star3"> 3 &ensp;</label>
                                                <input type="radio" id="star4" name="atmospere" value="4" />
                                                <label for="star4"> 4 &ensp;</label>
                                                <input type="radio" id="star5" name="atmospere" value="5" />
                                                <label for="star5"> 5 &ensp;stelle</label>
                                            </fieldset>

                                        </div>
                                    </div>                                   

                                    <div class="row">
                                        <div class="col-md-12">
                                            <button style="align-items: left" type="submit"  class="btn btn-success pull-right"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span> Ok</button> 
                                            
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>

                </div>                     
            </div>
        </div>   
    </div>

    <% for (Review rew : R.getReviews()) {%>

    <div class="modal-dialog modal-lg" >
        <div class="modal-content colonna2">
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-9">
                        <p style="color: black; font-size: 25px"><span class="glyphicon glyphicon-user" aria-hidden="true"></span><b> <%=rew.getCreator().getNickname()%></b></p>
                        <div id="tagline">
                            <p style="font-size: 25px;"><b><%= rew.getName()%></b></p>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <button style="" type="button" class="btn-like btn-default">
                            <span style="font-size: 25px;" class="glyphicon glyphicon-thumbs-up" aria-hidden="true">123</span>
                        </button>

                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <p style="color: #333333; font-size: 20px; margin-bottom: 0px; border-bottom: 0px;"><%= rew.getDescription()%></p>  

                        <hr align=”left” size=”1″ width=”300″ style="border-top-color: grey;" noshade>

                        <a href="#risposta" class="btn btn-info pull-right" data-toggle="collapse" ><span class="glyphicon glyphicon-edit"></span> Rispondi</a>
                    </div> 
                </div>

                <div id="risposta" class="collapse">
                    <div class="row">
                        <div class="col-md-12">    

                            <div class="row">
                                <div class="col-md-12">
                                    <div id="tagline">
                                        <p style="color: black; font-size: 20px; padding-top: 15px;"><b>Risposta:</b></p>
                                    </div>
                                </div>

                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-group">

                                        <textarea class="form-control" rows="5" id="comment"></textarea>
                                    </div>
                                </div>

                            </div>

                            <div class="row">
                                <div class="col-md-12">
                                    <button style="align-items: left" type="button" class="btn btn-success pull-right"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span> Ok</button> 
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>   
    </div>

    <% }%>   

</body>
</html>
