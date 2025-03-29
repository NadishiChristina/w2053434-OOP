import React, { useEffect, useState } from "react";
import axios from "axios";

export default function Home() {

  /*Configuration form */
  const [formData, setFormData] = useState({
    totalTickets: "",
    ticketReleaseRate: "",
    customerRetrievalRate: "",
    maxTicketCapacity: ""
  });

  const [errors, setErrors] = useState({});
  const [sales, setSales] = useState([]); /*Storing sales information*/

  useEffect(() => {
    loadSales(); /*to render updated sales info everytime page is refreshed*/
  }, []);

    /*Configuration validation */
  const validate = () => {
    const newErrors = {};
    if (!formData.totalTickets || isNaN(formData.totalTickets)) {
      newErrors.totalTickets = "Total tickets must be a valid number.";
    }
    if (!formData.ticketReleaseRate || isNaN(formData.ticketReleaseRate)) {
      newErrors.ticketReleaseRate = "Ticket release rate must be a valid number.";
    }
    if (!formData.customerRetrievalRate || isNaN(formData.customerRetrievalRate)) {
      newErrors.customerRetrievalRate = "Customer retrieval rate must be a valid number.";
    }
    if (!formData.maxTicketCapacity || isNaN(formData.maxTicketCapacity)) {
      newErrors.maxTicketCapacity = "Max capacity must be a valid number.";
    }
    return newErrors;
  };

  /*Configure button - validation*/
  const configureSystem = async () => {
    try {
      const validationErrors = validate();
      if (Object.keys(validationErrors).length > 0) {
        setErrors(validationErrors);
        return;
      }

    /*API calls*/
    /*URL link to set configurations*/
      const response = await axios.post("http://localhost:8080/config/set", {
        totalTickets: parseInt(formData.totalTickets),
        ticketReleaseRate: parseInt(formData.ticketReleaseRate),
        customerRetrievalRate: parseInt(formData.customerRetrievalRate),
        maxTicketCapacity: parseInt(formData.maxTicketCapacity)
      });

      alert(response.data);
    } catch (error) {
      console.error("Error configuring the system:", error);
      alert("Failed to configure the system.");
    }
  };

  const startTicketing = async () => {
    try {
      const response = await axios.post("http://localhost:8080/config/start-ticketing");
      alert(response.data);
    } catch (error) {
      console.error("Error starting ticketing system:", error);
      alert("Failed to start ticketing system.");
    }
  };

  const stopTicketing = async () => {
    try {
      const response = await axios.post("http://localhost:8080/config/stop-ticketing");
      alert(response.data);
    } catch (error) {
      console.error("Error stopping ticketing system:", error);
      alert("Failed to stop ticketing system.");
    }
  };

  const onInputChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  const loadSales = async () => {
    try {
      const result = await axios.get("http://localhost:8080/api/ticket-sales");
      setSales(result.data);
    } catch (error) {
      console.error("Error loading ticket sales:", error);
    }
  };

    /* render Configuration form and sales table */
  return (
    <div className="container">
      <div className="py-4">
        <form>
          <fieldset>
            <h3 className="text-center m-4">Configuration Form</h3>
          </fieldset>
          <div className="row g-3">
            <div className="col">
              <input
                type="text"
                className={`form-control ${errors.totalTickets ? "is-invalid" : ""}`}
                placeholder="Enter total tickets"
                name="totalTickets"
                value={formData.totalTickets}
                onChange={onInputChange}
              />
              {errors.totalTickets && (
                <div className="invalid-feedback">{errors.totalTickets}</div>
              )}
            </div>
            <div className="col">
              <input
                type="text"
                className={`form-control ${errors.ticketReleaseRate ? "is-invalid" : ""}`}
                placeholder="Enter ticket release rate"
                name="ticketReleaseRate"
                value={formData.ticketReleaseRate}
                onChange={onInputChange}
              />
              {errors.ticketReleaseRate && (
                <div className="invalid-feedback">{errors.ticketReleaseRate}</div>
              )}
            </div>
            <div className="col">
              <input
                type="text"
                className={`form-control ${errors.customerRetrievalRate ? "is-invalid" : ""}`}
                placeholder="Enter customer retrieval rate"
                name="customerRetrievalRate"
                value={formData.customerRetrievalRate}
                onChange={onInputChange}
              />
              {errors.customerRetrievalRate && (
                <div className="invalid-feedback">{errors.customerRetrievalRate}</div>
              )}
            </div>
            <div className="col">
              <input
                type="text"
                className={`form-control ${errors.maxTicketCapacity ? "is-invalid" : ""}`}
                placeholder="Enter max capacity"
                name="maxTicketCapacity"
                value={formData.maxTicketCapacity}
                onChange={onInputChange}
              />
              {errors.maxTicketCapacity && (
                <div className="invalid-feedback">{errors.maxTicketCapacity}</div>
              )}
            </div>
          </div>
        </form>
        <div className="d-grid gap-2 col-6 mx-auto mt-2">
          <button className="btn btn-primary" type="button" onClick={configureSystem}>
            Configure System
          </button>
        </div>

        <button
          type="button"
          className="btn btn-secondary mx-2 mt-2"
          onClick={startTicketing}
        >
          START
        </button>

        <button
          type="button"
          className="btn btn-secondary mx-2 mt-2"
          onClick={stopTicketing}
        >
          STOP
        </button>

        <button type="button" 
        className="btn btn-secondary mx-2 mt-2">
          RESET
        </button>
      </div>

      <h3 className="text-center m-1">Ticket Sales</h3>
      {/*Ticket Sales Table - Bootstrap Table*/}
      <div className="py-4">
        <table className="table border shadow">
          <thead>
            <tr>
              <th scope="col">Sales ID</th>
              <th scope="col">Customer ID</th>
              <th scope="col">Ticket Id</th>
              <th scope="col">Purchase Time</th>
            </tr>
          </thead>
          <tbody>
            {sales.map((sale, index) => (
              <tr key={index}>
                <td>{sale.id}</td>
                <td>{sale.customerId}</td>
                <td>{sale.ticket}</td>
                <td>{sale.purchaseTime}</td>
                <td>
                  <span className="badge text-bg-success">Purchased</span>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}
