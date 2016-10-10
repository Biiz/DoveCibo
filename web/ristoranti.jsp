<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DoveCibo</title>

        <style>
            body {
                background-image: url("img/img (7)b.jpg");
                background-repeat: no-repeat;
                background-attachment: fixed;
                background-size: cover;
            }
            .product .img-responsive {
                margin: 0 auto;
            }

            /* centered columns styles */
            .row-centered {
                text-align:center;
            }
            .col-centered {
                display:inline-block;
                float:none;
                /* reset the text-align */
                text-align:left;
                /* inline-block space fix */
                margin-right:-4px;
            }
            .col-fixed {
                /* custom width */
                width:320px;
            }
            .col-min {
                /* custom min width */
                min-width:320px;
            }
            .col-max {
                /* custom max width */
                max-width:320px;
            }
            .colonna2{
                background-color: rgba(255, 255, 255, 0.80);
                box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.5), 0 6px 20px 0 rgba(0, 0, 0, 0.7);
                border-radius: 5px;
            }
            .colonna1{
                max-width: 70%;
                margin-bottom: 25px;
            }
            div.polaroid {
                text-align: center;
            }
            div.polaroid:hover{
                box-shadow: 0px 0px 20px 0 rgba(255, 255, 255, 1);
                overflow: visible;
                z-index: auto;
                -webkit-margin-before: 0;
                -webkit-margin-after: 0;
                -webkit-margin-start: 0;
                -webkit-margin-end: 10px;
            }
            div.container {
                padding: 10px;
            }
            #no-rounded{
                border-bottom-right-radius: 0px;
                border-bottom-left-radius: 0px;
            }
        </style>
    </head>
    <body>
        <%@ include file="navBar.jsp" %>
        <br>
        
        <!-- bottoni-radio (toggle di java) che si possono premere uno per volta -->
        <div class="row row-centered" style="padding-bottom: 20px;">
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 ">
                <div data-toggle="buttons">
                    <label class="btn btn-info btn-lg active" style="margin-bottom: 10px;">
                        <input type="radio" name="valutazione" id="option1" checked> Valutazione
                    </label>
                    <label class="btn btn-info btn-lg" style="margin-bottom: 10px;">
                        <input type="radio" name="fascia_di_prezzo" id="option2"> Fascia di prezzo
                    </label>
                    <label class="btn btn-info btn-lg" style="margin-bottom: 10px;">
                        <input type="radio" name="vicinanza" id="option3" > Vicinanza
                    </label>
                </div>
            </div>
        </div>

        <div class="container">
            <div class="row row-centered">
                
<%
        Cookie cookie[] = request.getCookies();
        String nickname = null;
        if(cookie != null){
            for (int j = 0;j<cookie.length;j++){
                if(cookie[j].getValue().equals("1") || cookie[j].getValue().equals("2") || cookie[j].getValue().equals("3")){
                    nickname = cookie[j].getName();
                }
            }
            
        }
        
        DoveCiboPK.User u = new DoveCiboPK.User(-1, "", "", nickname, "", "", "");
        (new DoveCiboPK.DB_Manager()).CheckProfilo(u);
        
        //Integer count [] = {0};
        List<Integer> id_restaurant = new ArrayList<Integer>();
        (new DoveCiboPK.DB_Manager()).Prova(u, id_restaurant);
        Iterator itr = id_restaurant.iterator();
        
        
        while(itr.hasNext()) {
            Integer element = (Integer)itr.next();
            DoveCiboPK.Restaurant res = new DoveCiboPK.Restaurant(element, "", "", "", null, null, null, null);
            (new DoveCiboPK.DB_Manager()).cercaRistorante_perId(res);
          
%>
                <!-- pannello del ristorante-->
                <div class="col-xs-12 col-sm-12 col-md-4 col-lg-3 col-centered colonna1" onclick="window.location.href = 'ristorante.jsp'" style="padding: none;">
                    <div class="polaroid colonna2" >
                        <img src="img/img (5).jpg" class="img-responsive img-rounded" id="no-rounded" style="width:100%">
                        <div class="row">
                            <div class="col-md-12 text-center">
                                <p style="color: black; font-size: 28px"><b><%=res.getName()%></b></p>
                            </div>                 
                        </div>
                        <div class="row" style="padding-bottom: 10px; padding-top: 10px;">
                            <div class="col-md-12 text-center">
                                <span class="glyphicon glyphicon-cutlery" aria-hidden="true"></span> <b>
                                    <%
                                        List<Integer> id_cuisine = new ArrayList<Integer>();
                                        List<String> cuisine_name = new ArrayList<String>();
                                        (new DoveCiboPK.DB_Manager()).cercaCuisine_perId_Restaurant(res, id_cuisine);
                                        Iterator itr1 = id_cuisine.iterator();
                                        while(itr1.hasNext()){
                                            Integer element1 = (Integer)itr1.next();
                                            
                                            (new DoveCiboPK.DB_Manager()).cercaCuisine(element1, cuisine_name);
                                            
                                        }
                                        
                                        Iterator itr2 = cuisine_name.iterator();
                                        
                                        while(itr2.hasNext()){
                                            
                                            String element1 = (String)itr2.next();
                                            if(itr2.hasNext()){
                                        %>
                                                 <%=element1+", "%>
                                        <%
                                            }else{
                                        %>
                                                 <%=element1+""%>
                                            
                                        <%
                                            }
                                        }
                                        %>
                                </b>
                                <br>
                                <span class="glyphicon glyphicon-euro" aria-hidden="true"></span> <b>
                                <%
                                    Integer prezzo[] = {0,0};
                                    (new DoveCiboPK.DB_Manager()).cercaPriceRanges_Restaurant(res, prezzo);
                                    
                                %>
                                <%=prezzo[0]+" - "+prezzo[1]%>
                                </b>
                                <span class="glyphicon glyphicon-minus" aria-hidden="true"></span> 
                                <span class="glyphicon glyphicon-star" aria-hidden="true"></span> <b>Valutazione</b>
                                <br>
                                <span class="glyphicon glyphicon-map-marker" aria-hidden="true"></span> <b>
                                <%
                                    String address[] = {"w"};
                                    (new DoveCiboPK.DB_Manager()).cercaCoordinate_perID_Restaurant(res, address);
                                %>
                                <%=address[0]%>
                                </b>
                            </div>
                        </div>
                    </div>
                </div>
                <%
                  
                }
                %>

            </div>
        </div>  

    </body>
</html>
