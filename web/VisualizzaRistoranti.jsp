<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">        
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.12/css/dataTables.bootstrap.min.css">
        
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>      
        <script src="http://code.jquery.com/jquery-1.12.3.js"></script>
        <script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.10.12/js/dataTables.bootstrap.min.js"></script>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <style>
            body {
                background-image: url("img/img (7)b.jpg");
                background-repeat: no-repeat;
                background-attachment: fixed;
                background-size: cover;
            }  
        </style>
    </head>
    
    <body style="padding-top: 70px;">
        <%@ include file="navBar.jsp" %>

        <div class="container">        
            <div class="row row-centered">

                <table id="DataTable" class="table table-striped table-bordered" cellspacing="0" width="100%">
                    <thead>
                        <tr style="background-color: rgba(198, 239, 255, 1);">
                            <th>NOME</th>
                            <th>CLASSIFICA</th>
                            <th>REVIEWS</th>
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
                                    <%
                                        String res_name = (String)session.getAttribute("res_name" + i);
                                    %>
                                    <button class="btn btn-info btn-justified" type="submit"><b><%=res_name.substring(0, 1).toUpperCase() + res_name.substring(1)%></b></button>
                                </div>
                                <br>
                            </td>
                            <td>123</td>
                            <td>123</td>
                            <%
                                String res_city = (String)session.getAttribute("res_city" + i);
                                String res_address = (String)session.getAttribute("res_address" + i);
                            %>
                            <td><%=res_address.substring(0, 1).toUpperCase() + res_address.substring(1)%>, <%=res_city.substring(0, 1).toUpperCase() + res_city.substring(1)%></td>
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
            
                    $(document).ready(function() {
                        $('#DataTable').DataTable( {
                            "columnDefs": [
                                {
                                    "targets": [ 6 ],
                                    "visible": false,
                                    "searchable": false
                                }
                                
                            ]
                        } );
                    } );
                    
                    
                    $(document).ready(function() {
                        var table = $('#DataTable').DataTable( {
                            "scrollY": "200px",
                            "paging": false
                        } );

                        $('a.toggle-vis').on( 'click', function (e) {
                            e.preventDefault();

                            // Get the column API object
                            var column = table.column( $(this).attr('data-column') );

                            // Toggle the visibility
                            column.visible( ! column.visible() );
                        } );
                    } );
                </script>
                
            </div>
        </div>       
    </body>
</html>
