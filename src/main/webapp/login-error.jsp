<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Authentication error</title>
</head>
<body>
<h3 style="color:red">There was an error logging in. Please, try once again...</h3>

<form method=post action="j_security_check" >
    <table>
        <tr>
            <td>Username :</td>
            <td>
                <input type="text" name="j_username"/>
            </td>
        </tr>

        <tr>
            <td>Password :</td>
            <td>
                <input type="password" name="j_password"/>
            </td>
        </tr>

        <tr>
            <td></td>
            <td>
                <input type="submit" name="Login"/>
            </td>
        </tr>

    </table>
</form>
</body>
</html>
