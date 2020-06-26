import React, {Component} from 'react';
import Button from './Button'
import { ButtonGroup, ButtonToolbar } from 'react-bootstrap'

class Question extends Component{

  constructor(props) {
      super(props)
      this.state = {
        "speaking": false,
        "selectedAns":""
      }
  }


  setSelectedAns(selectedAns){
        this.setState({selectedAns});
        console.log('setSelectedAns - Selected Answer Option is:'+selectedAns)
  }

  clickButton = (questionid, label) => {
      console.log('Submitted Answer Option - Button clicked:')
      const {setCurrent, setScore, question, setShowHelp, setHintCounter} = this.props;

      if(this.state.selectedAns == question.correct){
            console.log('clickButton - Selected Answer Option is:'+this.state.selectedAns)
            setScore(this.props.score+1);
      } else {
        console.log('clickButton - Selected Answer Option is wrong:'+this.state.selectedAns)
      }
      setCurrent(this.props.current+1);
      setShowHelp(false);
      setHintCounter(0)


      if (this.props.renderView == "Quiz"){
          //ws://192.168.1.10:8080/api
          let socket = new window.WebSocket("ws://192.168.1.10:8080/api");

          const event1 = { event_name: "Notification", data: question.id };
          const event2 = { event_name: "Notification", data: question.id+1 };

          socket.onopen = function(e) {
            console.log("Socket Connection established");
            console.log("Sending to furhat");
            socket.send(JSON.stringify(event1));
            socket.send(JSON.stringify(event2));
            socket.close(1000, "Work complete");
            console.log("Socket Connection closed");
          };
      }

  }

  render(){
    const {question} = this.props;

    return(
      <div>
        <h3>{question.text}</h3>
        <hr/>
        <ul className="list-group">
          {
            this.props.question.choices.map(choice =>{
              return(
                <li className="list-group-item" key={choice.id}>
                  <input type='radio' onChange={() => this.setSelectedAns(choice.id)} name={question.id} value={choice.id} /> {choice.text}
                </li>
                )
            })
          }
        </ul>
        <div>
            <ButtonToolbar className="justify-content-between">
            <Button key="Submit" label="Submit" onClick={() => this.clickButton(question.id, "Submit")} speaking={this.state.speaking} style="info" bsSize="xsmall"/>
            <Button key="Skip" label="Skip" onClick={() => this.clickButton(question.id, "Skip")} speaking={this.state.speaking} style="info" bsSize="xsmall"/>
            </ButtonToolbar>
        </div>
      </div>
      )
  }
}

export default Question
