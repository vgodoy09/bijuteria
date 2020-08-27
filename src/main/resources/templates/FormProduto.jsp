<%@page import="les22018.core.util.ConverteDate"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page
	import="les22018.core.aplicacao.Resultado, les22018.dominio.*, java.util.*"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>:::: CADASTRO DE PRODUTO::::</title>
</head>
<body>

	<a href="index.html">Home</a>
	<a href="FormConsultaProduto.jsp"> | Consultar Produtos</a>

	<%
		Produto produto = (Produto) session.getAttribute("produto");
	
	Resultado resultadoMarca = (Resultado)getServletContext().getAttribute("resultadoMarca");
	Resultado resultadoModelo = (Resultado)getServletContext().getAttribute("resultadoModelo");
	
		List<EntidadeDominio> marcas = resultadoMarca.getEntidades();
		List<EntidadeDominio> modelos = resultadoModelo.getEntidades();
				
	%>

	<form action="SalvarProduto" method="post">
		<label for="txtId">Id:</label> <input type="text" id="txtId"
			name="txtId"
			value=<%if (produto != null)
				out.print("'" + produto.getId() + "' readonly>");
			else
				out.print(">");%></input>


		</br> <label for="txtDescricao">Descrição:</label> <input type="text"
			id="txtDescricao" name="txtDescricao"
			value=<%if (produto != null)
				out.print("'" + produto.getDescricao() + "'>");
			else
				out.print(">");%></input>

		</br> <label for="txtQtd">Quantidade:</label> <input type="text"
			id="txtQtd" name="txtQtd"
			value=<%if (produto != null)
				out.print("'" + produto.getQuantidade() + "'>");
			else
				out.print(">");%></input>

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
		
		</br> 

		<%
			if (produto != null) {
				String dtCadastro = ConverteDate.converteDateString(produto.getDtCadastro());
				out.print("<label for='txtDtCadastro'>Data de Cadastro:</label>");
				out.print("<input type='text' id='txtDtCadastro' name='txtDtCadastro' value='" + dtCadastro
						+ "' readonly>");
			}
		%>

		</input>


		<%
			if (produto != null) {
				out.print("<input type='submit' id='operacao' name='operacao' value='ALTERAR'/>");
				out.print("<input type='submit' id='operacao' name='operacao' value='EXCLUIR'/>");
			} else {
				out.print("<input type='submit' id='operacao' name='operacao' value='SALVAR'/>");
			}
		%>


	</form>
</body>
</html>