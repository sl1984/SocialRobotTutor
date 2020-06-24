import React, {Component} from 'react';
import Button from './Button'

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

  clickButton = (message) => {
      console.log('Submitted Answer Option - Button clicked:')
      const {setCurrent, setScore, question, setShowHelp} = this.props;

      if(this.state.selectedAns == question.correct){
            console.log('clickButton - Selected Answer Option is:'+this.state.selectedAns)
            setScore(this.props.score+1);
      }
      setCurrent(this.props.current+1);
      setShowHelp(false);


      //ws://192.168.1.10:8080/api
      let socket = new window.WebSocket("ws://192.168.1.10:8080/api");

      const event1 = { event_name: "Notification", data: question.id };
      const event2 = { event_name: "Notification", data: question.id+1 };

      socket.onopen = function(e) {
        console.log("[open] Connection established");
        console.log("Sending to server");
        socket.send(JSON.stringify(event1));
        socket.send(JSON.stringify(event2));
        socket.close(1000, "Work complete");
      };

  }

  render(){
    const {question} = this.props;
    var help = "The correct answer is: Option "+ question.correct

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
        <Button key="Submit Answer" label="Submit Answer" onClick={() => this.clickButton(question.id)} speaking={this.state.speaking} style="info"/>
      </div>
      )
  }
}

export default Question
