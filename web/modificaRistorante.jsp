<%@page import="restaurants.Cusine"%>
<%@page import="restaurants.Restaurant"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="./bootstrap-slider/bootstrap-slider.css" media="all" />
        <link rel="stylesheet" type="text/css" href="/DoveCiboGit/css/modificheRistorante.css" />
        
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>      
        <script src="http://code.jquery.com/jquery-1.12.3.js"></script>
        <script src="./bootstrap-slider/bootstrap-slider.js"></script>
        <script type="text/javascript" src="/DoveCiboGit/script/goback_window.js"></script>
        <script type="text/javascript" src="/DoveCiboGit/script/slider.js"></script>
        
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    
    <body style="padding-top: 70px;">
        <%@ include file="navBar.jsp" %>
        <%  
            Restaurant R = (Restaurant) request.getSession(false).getAttribute("ristorante"); 
        %>
        <form name="modifyRestaurantForm"  action="ServletModificaRistorante" method="post">
            <div class="container">
                <div class="row">
                    <div class="col-md-3 col-sm-2 col-xs-1"></div>
                    <div class="col-md-6 col-sm-8 col-xs-10 colonna2">
                        <div id="tagline">
                            <h1>Modifica Ristorante</h1>
                        </div>
                        <hr align=”left” size=”1″ width=”300″ style="border-top-color: grey;" noshade>

                        <!-- 1° row: nome e link-->
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>Nome Ristorante:</label>          
                                    <input type="text" id="nome_ristorante" class="form-control" name="nome_ristorante"  pattern=".{3,255}" title="Inserisci il nome" value="<%=R.getName().substring(0, 1).toUpperCase() + R.getName().substring(1)%>" required>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>Link al sito ufficiale:</label>          
                                    <input type="url" id="link" class="form-control" name="link" pattern="https?://.+.{3,255}" title="Aggiungi http://www.example.com" value="<%=R.getWeb_site_url()%>" required>
                                </div>
                            </div>
                        </div>

                        <hr align=”left” size=”1″ width=”300″ style="border-top-color: grey;" noshade>

                        <!-- 2° row: nazione e città-->
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>Nazione:</label>          
                                    <input type="text" id="txtNazione" class="form-control" name="txtNazione" pattern=".{3,255}" title="Inserisci la nazione" value="<%=R.getCordinate().getNazione().substring(0, 1).toUpperCase()+R.getCordinate().getNazione().substring(1)%>" required>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>Città:</label>          
                                    <input type="text" id="txtCity" class="form-control" name="txtCity" pattern=".{3,255}" title="Inserisci la città" value="<%=R.getCordinate().getCity().substring(0, 1).toUpperCase()+R.getCordinate().getCity().substring(1) %>" required>
                                </div>
                            </div>
                        </div>

                        <!-- 3° row: indirizzo-->
                        <div class="row">
                            <div class="col-md-9">
                                <div class="form-group">
                                    <label>Via:</label>
                                    <input type="text" id="txtVia" class="form-control" name="txtVia" pattern=".{3,255}" title="Inserisci la via" value="<%=R.getCordinate().getAdrers()%>" required>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label>Civico:</label>          
                                    <input type="number" id="txtNumero" class="form-control" name="txtNumero" placeholder="123" pattern=".{1,255}" min ="1" title="Inserisci il numero civico" value="<%=R.getCordinate().getNumero()%>" required>
                                </div>
                            </div>
                        </div>
                        <!-- 4° row: coordinate geografiche-->
                        <div class="row" > <%@include file="modificaRistoranteMap.jsp" %> </div>
                        <hr align=”left” size=”1″ width=”300″ style="border-top-color: grey;" noshade>

                        <!-- 5° row: descrizione ristorante-->
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="comment">Descrizione:</label>
                                    <textarea class="form-control" id="descrizione" rows="5" name="descrizione" pattern=".{3,32000}" title="Descrivi il ristorante" required><%=R.getDescription()%></textarea>
                                </div>
                            </div>
                        </div>

                        <hr align=”left” size=”1″ width=”300″ style="border-top-color: grey;" noshade>

                        <!-- 6° row: tipologia di cucina-->
                        <div class="row">
                            <div class="col-md-12">
                                <h4><b>Tipologie di cucina:</b></h4>
                            </div>
                        </div>
                        <%
                            ArrayList <String> cucine = new ArrayList<String>();
                            for(Cusine c : R.getCusines()){
                                cucine.add(c.getName());
                            }
                        %>
                        <div class="row">    
                            <div class="col-md-5">
                                <div class="funkyradio">
                                    <div class="funkyradio-info">
                                        <%
                                            if(cucine.contains("pizzeria")){
                                        %>
                                                <input type="checkbox" name="cb1" id="checkbox1" checked/>
                                        <%
                                            }else{
                                        %>
                                                <input type="checkbox" name="cb1" id="checkbox1"/>
                                        <%
                                            }
                                        %>
                                        <label for="checkbox1">Pizzeria</label>
                                    </div>
                                    <div class="funkyradio-info">
                                        <%
                                            if(cucine.contains("trattoria")){
                                        %>
                                                <input type="checkbox" name="cb2" id="checkbox2" checked/>
                                        <%
                                            }else{
                                        %>
                                                <input type="checkbox" name="cb2" id="checkbox2"/>
                                        <%
                                            }
                                        %>
                                        <label for="checkbox2">Trattoria</label>
                                    </div>
                                    <div class="funkyradio-info">
                                        <%
                                            if(cucine.contains("ristorante")){
                                        %>
                                                <input type="checkbox" name="cb3" id="checkbox3" checked/>
                                        <%
                                            }else{
                                        %>
                                                <input type="checkbox" name="cb3" id="checkbox3"/>
                                        <%
                                            }
                                        %>
                                        <label for="checkbox3">Ristorante</label>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-1"></div>
                            <div class="col-md-5">
                                <div class="funkyradio">
                                    <div class="funkyradio-info">
                                        <%
                                            if(cucine.contains("disco restaurant")){
                                        %>
                                                <input type="checkbox" name="cb4" id="checkbox4" checked/>
                                        <%
                                            }else{
                                        %>
                                                <input type="checkbox" name="cb4" id="checkbox4"/>
                                        <%
                                            }
                                        %>
                                        <label for="checkbox4">Disco restaurant</label>
                                    </div>
                                    <div class="funkyradio-info">
                                        <%
                                            if(cucine.contains("straniera")){
                                        %>
                                                <input type="checkbox" name="cb5" id="checkbox5" checked/>
                                        <%
                                            }else{
                                        %>
                                                <input type="checkbox" name="cb5" id="checkbox5"/>
                                        <%
                                            }
                                        %>
                                        <label for="checkbox5">Straniera</label>
                                    </div>
                                    <div class="funkyradio-info">
                                        <%
                                            if(cucine.contains("altro")){
                                        %>
                                                <input type="checkbox" name="cb6" id="checkbox6" checked/>
                                        <%
                                            }else{
                                        %>
                                                <input type="checkbox" name="cb6" id="checkbox6"/>
                                        <%
                                            }
                                        %>
                                        <label for="checkbox6">Altro</label>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <br>

                        <hr align=”left” size=”1″ width=”300″ style="border-top-color: grey;" noshade>

                        <!-- 7° row: fascia di prezzo-->
                        <div class="row">
                            <div class="col-md-12 text-center">
                                <h4><b>Fascia di prezzo:</b></h4>
                                <div class="row">
                                    <input id="price" name="price" type="text" class="span2" value="" data-slider-min="0" 
                                    data-slider-max="100" data-slider-step="5" 
                                    data-slider-value="[<%=R.getPrice_range().getMin_value()%>,<%=R.getPrice_range().getMax_value()%>]"
                                    data-slider-ticks="[0, 20, 40, 60, 80, 100]" 
                                    data-slider-ticks-snap-bounds="30" 
                                    data-slider-ticks-labels='["€0", "€20", "€40", "€60", "€80", "€100"]'/> 
                                </div> <!-- fine row cena -->
                            </div>
                        </div>
                        <br>

                        <hr align=”left” size=”1″ width=”300″ style="border-top-color: grey;" noshade>                       

                        <!-- 5° row-->
                        
                        <div class="row">
                            <div class="col-md-12">
                                <label for="comment">Orari di apertura pranzo:</label>
                                <br>

                                <div class="col-md-3">
                                    <label for="sel1"> Dalle ore</label>
                                    <div class="input-group">
                                        <span class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span>
                                        <select class="form-control" id="sel1" name="StMh">
                                            <%
                                                switch(R.getDay_hours().getStart_H_M()){
                                                    case 9:
                                            %>
                                                    <option>9</option>
                                                    <option>10</option>
                                                    <option>11</option>
                                                    <option>12</option>
                                            <%
                                                        break;
                                                    case 10:
                                            %>
                                                    <option>10</option>
                                                    <option>9</option>
                                                    <option>11</option>
                                                    <option>12</option>
                                            <%
                                                        break;
                                                    case 11:
                                            %>
                                                    <option>11</option>
                                                    <option>9</option>
                                                    <option>10</option>
                                                    <option>12</option>
                                            <%
                                                        break;
                                                    case 12:
                                            %>
                                                    <option>12</option>
                                                    <option>9</option>
                                                    <option>10</option>
                                                    <option>11</option>
                                            <%
                                                        break;
                                                }
                                            %>
                                          </select>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <label for="sel1">minuti</label>
                                    <div class="input-group">
                                        <span class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span>
                                        <select class="form-control" id="sel1" name="StMm">
                                            <%
                                                switch(R.getDay_hours().getStart_M_M()){
                                                    case 0:
                                            %>
                                                    <option>0</option>
                                                    <option>15</option>
                                                    <option>30</option>
                                                    <option>45</option>
                                            <%
                                                        break;
                                                    case 15:
                                            %>
                                                    <option>15</option>
                                                    <option>0</option>
                                                    <option>30</option>
                                                    <option>45</option>
                                            <%
                                                        break;
                                                    case 30:
                                            %>
                                                    <option>30</option>
                                                    <option>0</option>
                                                    <option>15</option>
                                                    <option>45</option>
                                            <%
                                                        break;
                                                    case 45:
                                            %>
                                                    <option>45</option>
                                                    <option>0</option>
                                                    <option>15</option>
                                                    <option>30</option>
                                            <%
                                                        break;
                                                }
                                            %>
                                          </select>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <label for="sel1">Alle ore</label>
                                    <div class="input-group">
                                        <span class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span>
                                        <select class="form-control" id="sel1" name="FtMh">
                                            <%
                                                switch(R.getDay_hours().getEnd_H_M()){
                                                    case 13:
                                            %>
                                                    <option>13</option>
                                                    <option>14</option>
                                                    <option>15</option>
                                                    <option>16</option>
                                            <%
                                                        break;
                                                    case 14:
                                            %>
                                                    <option>14</option>
                                                    <option>13</option>
                                                    <option>15</option>
                                                    <option>16</option>
                                            <%
                                                        break;
                                                    case 15:
                                            %>
                                                    <option>15</option>
                                                    <option>13</option>
                                                    <option>14</option>
                                                    <option>16</option>
                                            <%
                                                        break;
                                                    case 16:
                                            %>
                                                    <option>16</option>
                                                    <option>13</option>
                                                    <option>14</option>
                                                    <option>15</option>
                                            <%
                                                        break;
                                                }
                                            %>
                                          </select>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <label for="sel1">minuti</label>
                                    <div class="input-group">
                                        <span class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span>
                                        <select class="form-control" id="sel1" name="FtMm">
                                            <%
                                                switch(R.getDay_hours().getEnd_M_M()){
                                                    case 0:
                                            %>
                                                    <option>0</option>
                                                    <option>15</option>
                                                    <option>30</option>
                                                    <option>45</option>
                                            <%
                                                        break;
                                                    case 15:
                                            %>
                                                    <option>15</option>
                                                    <option>0</option>
                                                    <option>30</option>
                                                    <option>45</option>
                                            <%
                                                        break;
                                                    case 30:
                                            %>
                                                    <option>30</option>
                                                    <option>0</option>
                                                    <option>15</option>
                                                    <option>45</option>
                                            <%
                                                        break;
                                                    case 45:
                                            %>
                                                    <option>45</option>
                                                    <option>0</option>
                                                    <option>15</option>
                                                    <option>30</option>
                                            <%
                                                        break;
                                                }
                                            %>
                                          </select>
                                    </div>
                                </div>               
                            </div>
                        </div>
                        
                        <!-- 5° row-->
                        <div class="row">
                            <div class="col-md-12">
                                <label for="comment">Orari di apertura cena:</label>
                                <br>

                                <div class="col-md-3">
                                    <label for="sel1"> Dalle ore</label>
                                    <div class="input-group">
                                        <span class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span>
                                        <select class="form-control" id="sel1" name="StPh">
                                            <%
                                                switch(R.getDay_hours().getStart_H_P()){
                                                    case 17:
                                            %>
                                                    <option>17</option>
                                                    <option>18</option>
                                                    <option>19</option>
                                                    <option>20</option>
                                            <%
                                                        break;
                                                    case 18:
                                            %>
                                                    <option>18</option>
                                                    <option>17</option>
                                                    <option>19</option>
                                                    <option>20</option>
                                            <%
                                                        break;
                                                    case 19:
                                            %>
                                                    <option>19</option>
                                                    <option>17</option>
                                                    <option>18</option>
                                                    <option>20</option>
                                            <%
                                                        break;
                                                    case 20:
                                            %>
                                                    <option>20</option>
                                                    <option>17</option>
                                                    <option>18</option>
                                                    <option>19</option>
                                            <%
                                                        break;
                                                }
                                            %>
                                          </select>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <label for="sel1">minuti</label>
                                    <div class="input-group">
                                        <span class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span>
                                        <select class="form-control" id="sel1" name="StPm">
                                            <%
                                                switch(R.getDay_hours().getStart_M_P()){
                                                    case 0:
                                            %>
                                                    <option>0</option>
                                                    <option>15</option>
                                                    <option>30</option>
                                                    <option>45</option>
                                            <%
                                                        break;
                                                    case 15:
                                            %>
                                                    <option>15</option>
                                                    <option>0</option>
                                                    <option>30</option>
                                                    <option>45</option>
                                            <%
                                                        break;
                                                    case 30:
                                            %>
                                                    <option>30</option>
                                                    <option>0</option>
                                                    <option>15</option>
                                                    <option>45</option>
                                            <%
                                                        break;
                                                    case 45:
                                            %>
                                                    <option>45</option>
                                                    <option>0</option>
                                                    <option>15</option>
                                                    <option>30</option>
                                            <%
                                                        break;
                                                }
                                            %>
                                          </select>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <label for="sel1">Alle ore</label>
                                    <div class="input-group">
                                        <span class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span>
                                        <select class="form-control" id="sel1" name="FtPh">
                                            <%
                                                switch(R.getDay_hours().getEnd_H_P()){
                                                    case 21:
                                            %>
                                                    <option>21</option>
                                                    <option>22</option>
                                                    <option>23</option>
                                            <%
                                                        break;
                                                    case 22:
                                            %>
                                                    <option>22</option>
                                                    <option>21</option>
                                                    <option>23</option>
                                            <%
                                                        break;
                                                    case 23:
                                            %>
                                                    <option>23</option>
                                                    <option>21</option>
                                                    <option>22</option>
                                            <%
                                                        break;
                                                }
                                                
                                            %>
                                          </select>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <label for="sel1">minuti</label>
                                    <div class="input-group">
                                        <span class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span>
                                        <select class="form-control" id="sel1" name="FtPm">
                                            <%
                                                switch(R.getDay_hours().getEnd_M_P()){
                                                    case 0:
                                            %>
                                                    <option>0</option>
                                                    <option>15</option>
                                                    <option>30</option>
                                                    <option>45</option>
                                            <%
                                                        break;
                                                    case 15:
                                            %>
                                                    <option>15</option>
                                                    <option>0</option>
                                                    <option>30</option>
                                                    <option>45</option>
                                            <%
                                                        break;
                                                    case 30:
                                            %>
                                                    <option>30</option>
                                                    <option>0</option>
                                                    <option>15</option>
                                                    <option>45</option>
                                            <%
                                                        break;
                                                    case 45:
                                            %>
                                                    <option>45</option>
                                                    <option>0</option>
                                                    <option>15</option>
                                                    <option>30</option>
                                            <%
                                                        break;
                                                }
                                            %>
                                          </select>
                                    </div>
                                </div>                
                            </div>
                        </div>
                        <br>
                        
                        <hr align=”left” size=”1″ width=”300″ style="border-top-color: grey;" noshade>

                        <!-- 10° row: bottoni-->
                        <div class="row">
                            <div class="col-md-12">
                                <button type="submit" class="btn btn-success btn-lg pull-right"><span class="glyphicon glyphicon glyphicon-ok"></span> Conferma</button>
                                <button type="reset" class="btn btn-sm btn-danger" onclick="goBack()"><span class="glyphicon glyphicon-backward"></span> Annulla</button>
                                <button type="reset" class="btn btn-sm btn-warning"><span class="glyphicon glyphicon-remove"></span> Cancella Campi</button>
                            </div>
                        </div>
                        <br>
                    </div>
                    <div class="col-md-3 col-sm-2 col-xs-1"></div>
                </div><br>
            </div>    
        </form>
    </body>
</html>
