<%@ page import="sun.misc.Request" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Mortgage Plan</title>
</head>
<body>
<div>
<h1><%= "Mortgage Plan" %>
</h1>
<p>Check your Monthly payment</p>
<br/>
<form action="calculate" method="post">
    <label>Your Full Name:</label>
    <input type="text" name="name" placeholder="Enter your name here" required/>

    <label>Total Loan Amount: (Euro)</label>
        <input type="number" min="0.01" step=".01" name="loanAmount" placeholder="Loan Amount" required/>

    <label>Yearly Interest rate:</label>
        <input type="number" min="0.01" step=".01" name="interestRate" placeholder="Interest Rate Yearly" required/>

    <label>Loan Payback in years:</label>
        <input type="number" min="0.01" step=".01" name="years" placeholder="How many years" required/>
    <input class="button" type="submit" value="calculate"/>
</form>
    <div>
    ${results}
    </div>
</div>
<style>
    div{
        margin: auto;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        box-sizing: border-box;
    }
    form{
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
    }
    label{
        margin-top:10px;
        font-weight: 700;
    }
    input{
        margin:10px;
        padding: 6px 10px 6px 10px;
        border-radius: 6px;
        border: 1px solid #0b5a0b;
        outline:none;
        text-align: center;
    }
    .button{
        margin: 20px;
        background-color: #1d7a1d;
        outline: none;
        border: transparent;
        padding:10px;
        color:white;
        border-radius: 6px;
        font-weight: bold;
    }
    button:hover{
        transform: scale(1.1);
    }
</style>

</body>
</html>