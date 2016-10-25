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
        </style>
    </head>
    <body>
        <%@ include file="navBar.jsp" %>

        <div class="container">
            <div class="row row-centered">



                <table id="DataTable" class="table table-striped table-bordered" cellspacing="0" width="100%">
                    <thead>
                        <tr style="background-color: rgba(198, 239, 255, 1);">
                            <th>NOME</th>
                            <th>VALUTAZIONE</th>
                            <th>€ MIN</th>
                            <th>€ MAX</th>
                            <th>INDIRIZZO</th>
                            <th>CUCINE</th>
                        </tr>
                    </thead>
                    <tbody style="background-color: white;">
                        <%                            
                            List id_restaurant = (List) session.getAttribute("id_restaurant");
                            for (int i = 0; i < id_restaurant.size(); i++) {
                        %>
                    <form name="ApriRistorante" method="POST" action="">
                        <tr>
                            <td>
                                <!-- bottone SUBMIT che contiene il nome del ristorante -->
                                <div class="bottom text-center">
                                    <button class="btn btn-info btn-justified" type="submit"><b><%=session.getAttribute("res_name" + i)%></b></button>
                                </div>
                            </td>
                            <td>stelle</td>
                            <td><%=session.getAttribute("prezzo_min" + i)%></td>
                            <td><%=session.getAttribute("prezzo_max" + i)%></td>
                            <td><%=session.getAttribute("res_address" + i)%></td>
                            <td>
                                <%
                                    List cuisine_name = (List) session.getAttribute("cuisine_name" + i);

                                    Iterator itr2 = cuisine_name.iterator();

                                    while (itr2.hasNext()) {

                                    String element1 = (String) itr2.next();
                                        if (itr2.hasNext()) {
                                %>
                                <%=element1 + ", "%>
                                <%
                                    } else {
                                %>
                                <%=element1 + ""%>
                                <%
                                        }
                                    }
                                %>
                            </td>
                        </tr>
                    </form>
                            <%
                                }
                            %>
                    </tbody>
                </table>

                <script>
                    $(document).ready(function () {
                        $('#DataTable').DataTable();
                    });
                    
                    $('#DataTable').DataTable( {
                        responsive: true
                    } );
                </script>
                
            </div>
        </div>  

    </body>
</html>
