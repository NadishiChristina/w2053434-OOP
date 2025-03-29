import React from 'react'
import { Link } from 'react-router-dom'

export default function Navbar() {
  return (
    <div> 
        {/*Bootstrap NavBar*/}
        <nav className="navbar navbar-expand-lg navbar-dark bg-primary">
         <div className="container-fluid">
           <a className="navbar-brand" href="#">
             Event Ticketing System {/*Heading*/}
           </a>
            <button
            className="navbar-toggler" 
            type="button" 
            data-bs-toggle="collapse" 
            data-bs-target="#navbarSupportedContent" 
            aria-controls="navbarSupportedContent" 
            aria-expanded="false" 
            aria-label="Toggle navigation">
              <span className="navbar-toggler-icon"></span>
            </button>

            {/*Add Vendor Button*/}
            <Link className="btn btn-outline-light" to="/addticket">Add Vendor</Link> 
          </div>
        </nav>
    </div>
  )
}
