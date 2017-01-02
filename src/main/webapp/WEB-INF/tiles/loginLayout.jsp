<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title><tiles:insertAttribute name="title" ignore="true" /></title>
    </head>
    <body>
        <table cellpadding="2" cellspacing="2" width="800" align="center">
            <tr>
                <td width="590" height="600" valign="top">
                    <tiles:insertAttribute name="body" />
                </td>
            </tr>
           
        </table>
    </body>
</html>