<%@page import="les22018.core.util.ConverteDate"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page
	import="les22018.core.aplicacao.Resultado, les22018.dominio.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">

<title>:::: CONSULTAR PRODUTO::::</title>
</head>
<body>
	<a href="index.html">Home</a>
	<%
		Resultado resultado = (Resultado) session.getAttribute("resultado");

	Resultado resultadoMarca = (Resultado)getServletContext().getAttribute("resultadoMarca");
	Resultado resultadoModelo = (Resultado)getServletContext().getAttribute("resultadoModelo");
	
		List<EntidadeDominio> marcas = resultadoMarca.getEntidades();
		List<EntidadeDominio> modelos = resultadoModelo.getEntidades();
	%>

	<form action="SalvarProduto" method="post">

		<label for="txtId">Id:</label> <input type="text" id="txtId"
			name="txtId" /> </br> <label for="txtDescricao">DESCRIÇÃO:</label> <input
			type="text" id="txtDescricao" name="txtDescricao" /> <input
			type="submit" id="operacao" name="operacao" value="CONSULTAR" /> 
			
			</br>
			
			<label
			for="cbMarca">Marca:</label> 
			
			<select id="cbMarca" name="cbMarca">

			<%
				

				if (marcas != null) {
					for (EntidadeDominio m : marcas) {
						out.print("<option value='");
						out.print(m.getId());
						out.print("' ");						
						out.print(">");
						out.print(((Marca)m).getDescricao());
						out.print("</option>");
					}
				}
			%>
		</select>
		
		<label
			for="cbModelo">Modelo:</label> 
			
			<select id="cbModelo" name="cbModelo">

			<%
				

				if (modelos != null) {
					for (EntidadeDominio m : modelos) {
						out.print("<option value='");
						out.print(m.getId());
						out.print("' ");						
						out.print(">");
						out.print(((Modelo)m).getDescricao());
						out.print("</option>");
					}
				}
			%>
		</select>
	</form>


	<a href="FormProduto.jsp">Novo</a>

	<%
		if (resultado != null && resultado.getMsg() != null) {
			out.print(resultado.getMsg());
		}
	%>
	<BR>

	<TABLE BORDER="5" WIDTH="50%" CELLPADDING="4" CELLSPACING="3">
		<TR>
			<TH COLSPAN="3"><BR>
				<H3>PRODUTOS</H3></TH>
		</TR>

		<TR>
			<TH>ID:</TH>
			<TH>Descrição</TH>
			<TH>Quantidade:</TH>
			<TH>Marca:</TH>
			<TH>Modelo:</TH>
		</TR>


		<%
			if (resultado != null) {
				List<EntidadeDominio> entidades = resultado.getEntidades();
				StringBuilder sbRegistro = new StringBuilder();
				StringBuilder sbLink = new StringBuilder();

				if (entidades != null) {
					for (int i = 0; i < entidades.size(); i++) {
						Produto p = (Produto) entidades.get(i);
						sbRegistro.setLength(0);
						sbLink.setLength(0);

						//	<a href="nome-do-lugar-a-ser-levado">descrição</a>

						sbRegistro.append("<TR ALIGN='CENTER'>");

						sbLink.append("<a href=SalvarProduto?");
						sbLink.append("txtId=");
						sbLink.append(p.getId());
						sbLink.append("&");
						sbLink.append("operacao=");
						sbLink.append("VISUALIZAR");

						sbLink.append(">");

						sbRegistro.append("<TD>");
						sbRegistro.append(sbLink.toString());
						sbRegistro.append(p.getId());
						sbRegistro.append("</a>");
						sbRegistro.append("</TD>");

						sbRegistro.append("<TD>");
						sbRegistro.append(sbLink.toString());
						sbRegistro.append(p.getDescricao());
						sbRegistro.append("</a>");
						sbRegistro.append("</TD>");

						sbRegistro.append("<TD>");
						sbRegistro.append(sbLink.toString());
						sbRegistro.append(p.getQuantidade());
						sbRegistro.append("</a>");
						sbRegistro.append("</TD>");
						
						sbRegistro.append("<TD>");
						sbRegistro.append(sbLink.toString());
						sbRegistro.append(p.getMarca().getDescricao());
						sbRegistro.append("</a>");
						sbRegistro.append("</TD>");
						
						sbRegistro.append("<TD>");
						sbRegistro.append(sbLink.toString());
						sbRegistro.append(p.getModelo().getDescricao());
						sbRegistro.append("</a>");
						sbRegistro.append("</TD>");

						sbRegistro.append("</TR>");

						out.print(sbRegistro.toString());

					}
				}

			}
		%>




	</TABLE>


</body>
</html>