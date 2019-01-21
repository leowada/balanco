import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';

class App extends Component {

    state = {
        isLoading: true,
        userCustoms: []
    };

    async componentDidMount() {
        const response = await fetch('/api/userCustoms');
        const body = await response.json();
        console.log(body);
        this.setState({ userCustoms: body.content, isLoading: false });
    }

    render() {

        const {userCustoms, isLoading} = this.state;

        if (isLoading) {
            return <p>Carregando...</p>;
        }

        return (
            <div className="App">
                <header className="App-header">
                    <img src={logo} className="App-logo" alt="logo" />
                        <div className="App-intro">
                        <h2>Lista de usuários</h2>
                        {userCustoms.map(userCustom =>
                        <div key={userCustom.id}>
                            {userCustom.nome} {userCustom.sobrenome}
                        </div>
                        )}
                        </div>
                </header>
            </div>
        );
    }
}

export default App;
