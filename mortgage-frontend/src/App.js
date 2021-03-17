import "./App.css";
import { useState } from "react";
import axios from "axios";

function App() {
  const [user, setUser] = useState({
    name: "",
    loanAmount: 0,
    interestRate: 0,
    years: 0,
  });

  const [data, setData] = useState();

  const calculate = async (e) => {
    e.preventDefault();
    const res = await axios.post("/mortgage/calculate", user);
    const data = await res.data;
    setData(data);
    setUser({
      name: "",
      loanAmount: 0,
      interestRate: 0,
      years: 0,
    });
  };

  const handleChange = (e) => {
    setUser({
      ...user,
      [e.target.name]: e.target.value,
    });
  };
  return (
    <div className="App">
      <h1>Mortgage Plan</h1>
      <p>Check your Monthly payment</p>
      <br />

      <form onSubmit={(e) => calculate(e)} method="post">
        <label>Your Full Name:</label>
        <input
          type="text"
          name="name"
          value={user.name}
          onChange={(e) => handleChange(e)}
          placeholder="Enter your name here"
          required
        />

        <label>Total Loan Amount: (Euro)</label>
        <input
          type="number"
          value={user.loanAmount}
          min="0.01"
          step=".01"
          onChange={(e) => handleChange(e)}
          name="loanAmount"
          placeholder="Loan Amount"
          required
        />

        <label>Yearly Interest rate:</label>
        <input
          type="number"
          value={user.interestRate}
          min="0.01"
          step=".01"
          onChange={(e) => handleChange(e)}
          name="interestRate"
          placeholder="Interest Rate Yearly"
          required
        />

        <label>Loan Payback in years:</label>
        <input
          type="number"
          value={user.years}
          min="0.01"
          step=".01"
          onChange={(e) => handleChange(e)}
          name="years"
          placeholder="How many years"
          required
        />
        <input className="button" type="submit" value="calculate" />
      </form>
      {data && (
        <div>
          <h3> Result </h3>
          <p>
            You have to pay <b>{data.monthlyPayment}</b> € monthly to payback
            <b> {data.loanAmount}</b> € in <b>{data.years}</b> years with
            interest rate
            <b> {data.interestRate}%.</b>
          </p>
        </div>
      )}
    </div>
  );
}

export default App;
