<DIV align="center">
<CENTER>

    <TR>
        <TD width="100%" align="center"><%
        String n = (String)session.getAttribute("nickname");
        if (n != null && n.length() > 0)
        {
            out.write("<a href=\"logout.jsp\">Logout</a>");
        }
        %> </TD>
    </TR>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<font color="blue">Made By Krishnakanth Yachareni</font>

</CENTER>
</DIV>
