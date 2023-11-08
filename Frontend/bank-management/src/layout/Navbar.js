import React from 'react'
import { Link } from 'react-router-dom'

export default function Navbar() {
    return (
        <div>
            <nav className="navbar navbar-expand-lg navbar-dark bg-primary">
                <div className="container-fluid">
                    <a className="navbar-brand" href="/">ABC Bank</a>

                    <button className="navbar-toggler"
                        type="button">
                    </button>
                    <Link className='btn btn-outline-light' to="/register">Register</Link>
                    <Link className='btn btn-outline-light' to="/login">Login</Link>
                </div>
            </nav>
        </div>
    )
}
