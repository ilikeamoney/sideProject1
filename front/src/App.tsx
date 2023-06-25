import React, {Component} from 'react';
import "./App.css"


export default class App extends Component<any, any> {
    btnStyle = {
        color: "#fff",
        border: "none",
        padding: "5px 9px",
        borderRadius: "50%",
        cursor: "pointer",
        display: "flex",
    }

    getStyle = () => {
        return {
            padding: "10px",
            borderBottom: "1px #ccc dotted",
            textDecoration: "none",


        }
    }

    render() {
        return (
            <div className="container">
                <div className="todoBlock">
                    <div className="title">
                        <h1>해야 할 일</h1>
                    </div>

                    <div style={this.getStyle()}>
                        <input type={"checkbox"} defaultChecked={false} />
                        공부하기
                        <button style={this.btnStyle}>x</button>
                    </div>
                </div>
            </div>
        );
    }
}


