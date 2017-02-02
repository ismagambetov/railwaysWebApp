<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Authentication user</title>
</head>
<body>
    <h3>Please, enter Username and Password to access required page.</h3>

    <form method=post action="../controller/login" >
        <table>
            <tr>
                <td>Username :</td>
                <td>
                    <input type="text" name="username"/>
                </td>
            </tr>

            <tr>
                <td>Password :</td>
                <td>
                    <input type="password" name="password"/>
                </td>
            </tr>

            <tr>
                <td></td>
                <td>
                    <input type="submit" name="Login" value="Login"/>
                </td>
            </tr>

        </table>
    </form>

</body>
</html>
