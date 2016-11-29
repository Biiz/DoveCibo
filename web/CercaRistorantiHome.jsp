<%@page import="java.util.HashSet"%>
<%@page import="java.util.Set"%>
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

            tfoot input {
                width: 100%;
                padding: 3px;
                box-sizing: border-box;
            }



        </style>
    </head>
    <body>
        <%@ include file="navBar.jsp" %>

        <div class="container">
            <div class="row row-centered">

                <h2>Per cercare qualcosa tra i risultati utilizzare l'input di ricerca a destra.</h2>
                <h2>Per filtrare i risultati, utilizzare gli input sotto ad una colonna.</h2>
                <br><br>

                <table id="DataTable" class="table table-striped table-bordered" cellspacing="0" width="100%">
                    <thead>
                        <tr style="background-color: rgba(198, 239, 255, 1);">
                            <th>NOME</th>
                            <th>VALUTAZIONE</th>
                            <th>CLASSIFICA</th>
                            <th>REVIEWS</th>
                            <th>€ MIN</th>
                            <th>€ MAX</th>
                            <th>INDIRIZZO</th>
                            <th>CUCINE</th>
                        </tr>
                    </thead>
                    <tfoot>
                        <tr>
                            <th>Name</th>
                            <th>valutazione</th>
                            <th>classifica</th>
                            <th>review</th>
                            <th>min</th>
                            <th>max</th>
                            <th>indirizzo</th>
                            <th>cucine</th>
                        </tr>
                    </tfoot>
                    <tbody style="background-color: white;">
                        <%  Set id_restaurant = (HashSet) session.getAttribute("id_restaurant");
                            for (int i = 0; i < id_restaurant.size(); i++) {
                        %>
                    <form name="ApriRistorante" method="POST" action="">
                        <tr>
                            <td>
                                <!-- bottone SUBMIT che contiene il nome del ristorante -->
                                <div class="bottom text-center">
                                    <%
                                        String res_name = (String) session.getAttribute("res_name" + i);
                                    %>
                                    <b><a href='/DoveCiboGit/ServletGetRistorante?idR=<%=id_restaurant.toArray()[i]%> ' style="color: blue"><%=res_name.substring(0, 1).toUpperCase() + res_name.substring(1)%></a></b>
                                </div>
                                <br>
                                <div class="bottom text-center">
                                    <button class="btn btn-info btn-sm btn-justified">mappa</button>
                                </div>
                            </td>
                            <td background="img/img (1)low.jpg"><b style="background-color: white;">stelle</b></td>
                            <td>123</td>
                            <td>123</td>
                            <td><%=session.getAttribute("prezzo_min" + i)%></td>
                            <td><%=session.getAttribute("prezzo_max" + i)%></td>
                            <%
                                String res_city = (String) session.getAttribute("res_city" + i);
                                String res_address = (String) session.getAttribute("res_address" + i);
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
                    $(document).ready(function () {
                        $('#DataTable').DataTable();
                    });

                    $('#DataTable').DataTable({
                        responsive: true
                    });
                </script>

            </div>
        </div>  



        <script>
            $(document).ready(function () {
                // Setup - add a text input to each footer cell
                $('#DataTable tfoot th').each(function () {
                    var title = $(this).text();
                    $(this).html('<input type="text" placeholder="' + title + '.. " />');
                });

                // DataTable
                var table = $('#DataTable').DataTable();

                // Apply the search
                table.columns().every(function () {
                    var that = this;

                    $('input', this.footer()).on('keyup change', function () {
                        if (that.search() !== this.value) {
                            that
                                    .search( this.value )
                                .draw();
                        }
                    } );
                } );
            } );
</script>
                    
                    
    </body>
</html>
