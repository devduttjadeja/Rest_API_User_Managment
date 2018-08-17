

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Defect Details</title>

</head>

<body>
	<h3>Please Enter Your Defect Details</h3>


	<form action="/Rest_API_User_Managment/regestrationSuccess"
		method="post">

		<table>

			<tr>
				<td>Name :</td>
				<td><input type="text" name="name" required="required"> <br> <br></td>
			</tr>

			<tr>
				<td>Defect# :</td>
				<td><input type="number" name="defectid" required="required"> <br> <br></td>
			</tr>

			<tr>
				<td>Train :</td>
				<td><select name="train">
						<option value="Train 7">Train 7</option>
						<option value="Train 8">Train 8</option>
						<option value="Train 9">Train 9</option>
				</select><br> <br></td>
			</tr>

			<tr>
				<td>Silo/Squad :</td>
				<td><select name="squad">
						<option value="S2U IRD">S2U IRD</option>
						<option value="S2U Front Agent">S2U Front Agent</option>
						<option value="S2U Auto">S2U Auto</option>
						<option value="S2U Contract life (incl. Term)">S2U
							Contract life (incl. Term)</option>
						<option value="S2U Products">S2U Products</option>
				</select> <br> <br></td>
			</tr>

			<tr>
				<td>Comments :</td>
				<td><textarea name="comments" rows="3" cols="50" required="required"> </textarea> <br>
					<br></td>
			</tr>


			<tr>
				<td>Supported By :</td>
				<td><input type="text" name="supportedby"><br> <br></td>
			</tr>

			<tr>
				<td> <input type="submit" value="Save"> </td>
				<td> <input type="submit" value="Download File" onclick="form.action='/Rest_API_User_Managment/download';"> </td>
			</tr>

		</table>



	</form>

</body>
</html>