import axios from "axios";
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

export default function AddTicket() {
/*to navigate to home when submit is clicked */
  let navigate = useNavigate();

  const [ticket, setTicket] = useState({
    vendorName: "",
    eventname: "",
    price: ""
  });

  const [errors, setErrors] = useState({
    vendorName: '',
    eventname: '',
    price: ''
  })

  function validateForm(){
    let valid = true;

    const errorsCopy = {...errors}

    if(vendorName.trim()){
      errorsCopy.vendorName = '';
    } else {
      errorsCopy.vendorName = 'Vendor name required';
      valid = false;
    }

    if(eventname.trim()){
      errorsCopy.eventname = '';
    } else {
      errorsCopy.eventname = 'Event name required';
      valid = false;
    }

    if(price.trim()){
      errorsCopy.price = '';
    } else {
      errorsCopy.price = 'Price required';
      valid = false;
    }

    setErrors(errorsCopy);

    return valid;
  }

  const { vendorName, eventname, price } = ticket;

  const onInputChange = (e) => {
    setTicket({ ...ticket, [e.target.name]: e.target.value });
  };

/*prevent url change*/
  const onSubmit=async(e)=>{
    e.preventDefault();

    if (validateForm()){
    await axios.post("http://localhost:8080/vendor", ticket);
    navigate("/");
    }
  };/*to navigate to home when submit is clicked */


  return (
    <div className="container">
      <div className="row">
        <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
          <h2 className='text-center m-4'>Add Vendor</h2>

        <form onSubmit={(e) => onSubmit(e)}>        
          <div className='mb-3'>
            <label htmlFor='VendorName' className='form-label'>
            Vendor Name
            </label>
            <input
            type={"text"}
            className={`form-control ${ errors.vendorName ? 'is-invalid':''}`}                       
            placeholder="Enter your Vendor Name"
            name="vendorName"
            value={vendorName}
            onChange={(e)=>onInputChange(e)}
            />
            {errors.vendorName && <div className='invalid-feedback'> {errors.vendorName}</div>}
            </div>

            <div className='mb-3'>
            <label htmlFor='EventName' className='form-label'>
            Event Name
            </label>
            <input
            type={"text"}
            className={`form-control ${ errors.eventname ? 'is-invalid':''}`}                      
            placeholder="Enter your Event name"
            name="eventname"
            value={eventname}
            onChange={(e)=>onInputChange(e)}
            />
            {errors.eventname && <div className='invalid-feedback'> {errors.eventname}</div>}
          </div>

          <div className='mb-3'>
            <label htmlFor='Price' className='form-label'>
            Price
            </label>
            <input
            type={"text"}
            className={`form-control ${ errors.price ? 'is-invalid':''}`}                        
            placeholder="Enter your Ticket price"
            name="price"
            value={price}
            onChange={(e)=>onInputChange(e)}
            />
            {errors.price && <div className='invalid-feedback'> {errors.price}</div>}
          </div>

          <button type="submit" className='btn btn-outline-primary'>Register</button> 
          </form> 
          </div>
        </div>
      </div>
    );
}