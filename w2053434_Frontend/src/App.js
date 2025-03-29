import './App.css';
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import Navbar from './layout/Navbar';
import Home from './pages/Home';
import {BrowserRouter as Router, Routes, Route} from 'react-router-dom';
import AddTicket from './tickets/AddTicket';
function App() {
  return (

    <div className="App">
      <Router>
      <Navbar/>
      <Routes>
        <Route exact path="/" element={<Home />} />
        <Route exact path="/addticket" element={<AddTicket />} />    
      </Routes>
      </Router>
      
    </div>
  );
}

export default App;
