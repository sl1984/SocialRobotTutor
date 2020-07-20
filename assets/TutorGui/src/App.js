import React, {Component} from 'react'
import Furhat from 'furhat-gui'
import { Container, Grid, Row, Col, ButtonGroup, Navbar} from 'react-bootstrap'
import Button from './Button'
import Input from './Input'
import QuestionList from './QuestionList'
import ProgressBox from './ProgressBox'
import Results from './Results'
import Lecture from './Lecture'
import HelpBox from './HelpBox'


class App extends Component {

    constructor(props) {
        super(props)
        this.state = {
          "speaking": false,
          "showHelp": false,
          "buttons": [],
          "screen":[],
          "renderView": "",
          "inputFields": [],
          "preTestStartTime": "",
          "preTestAnswerDelay": "",
          "preTestEndTime": "",
          "questions":[
                  {
                      id:1,
                      text: 'If the resultant force on a body is zero, its velocity will not change',
                      choices:[
                        {
                          id:'a',
                          text:'True'
                        },
                        {
                          id:'b',
                          text:'False'
                        },
                      ],
                      correct: 'a',
                      hintone: "Newton’s first law of motion states that there must be a cause—which is a net external force—for there to be any change in velocity, either a change in magnitude or direction.",
                      hinttwo: "An object with no net forces acting on it would not have a change in velocity. If it is stationary, it would stay stationary. If it is in motion, it will stay in motion with a constant velocity . This comes directly out of Newton's First Law of Motion.",
                      hintthree: "A resultant force acting on a stationary object causes the object to move"
                    },
                  {
                    id:2,
                    text: "According to Newton's first law, when the net force acting on a moving object is zero, the object will",
                    choices:[
                      {
                        id:'a',
                        text:'accelerate'
                      },
                      {
                        id:'b',
                        text:'decelerate'
                      },
                      {
                        id:'c',
                        text:'move with constant speed'
                      },
                      {
                        id:'d',
                        text:'move with constant velocity'
                      },
                    ],
                    correct: 'd',
                    hintone: "We studied that  when an unbalanced force is exerted on a moving object , it causes it to speed up, slow down, stop or change direction. In case of balanced forces, the object maintains its state of motion.",
                    hinttwo: "If the forces are balanced, the object in uniform motion continues to move with same speed in the same direction. Velocity is dependant on both direction and speed",
                    hintthree: ""
                  },
                  {
                    id:3,
                    text: "Which of Newton's Laws explains why satellites need very little fuel to stay in orbit?",
                    choices:[
                      {
                        id:'a',
                        text:"Newton's First Law of Motion"
                      },
                      {
                        id:'b',
                        text:"Newton's Second Law of Motion"
                      },
                      {
                        id:'c',
                        text:"Newton's Third Law of Motion"
                      },
                    ],
                    correct: 'a',
                    hintone: "Which law states that’s objects continue to move in uniform motion unless acted by an external force? There are very little forces encountered in space, because it is near vacuum.",
                    hinttwo: "According to Newton’s first law, objects continue to move in uniform motion unless acted by an external force? There are very little forces encountered in space, because it is near vacuum.",
                    hintthree: ""
                  },
                  {
                    id:4,
                    text: "If a 10 kg cart is accelerating down the hill at 560 cm/sec2 , what is the force that is the size of the force causing the acceleration?",
                    choices:[
                      {
                        id:'a',
                        text:".56 N"
                      },
                      {
                        id:'b',
                        text:"56 N"
                      },
                      {
                        id:'c',
                        text:"5600 N"
                      },
                      {
                        id:'d',
                        text:"0 N"
                      },
                    ],
                    correct: 'b',
                    hintone: "Do you remember the formula of Newton's Second Law of Motion? What is the unit of acceleration?",
                    hinttwo: "Force (N)  = Mass (kg) × Acceleration m / s2 . Mass is measured in m/s2( 560cm/s2 equals 5.6m/s2)",
                    hintthree: ""
                  },
                  {
                    id:5,
                    text: "A force of 50 N is applied to an object with a mass of 500 gm. Calculate the acceleration of the object",
                    choices:[
                      {
                        id:'a',
                        text:'25 m/s2'
                      },
                      {
                        id:'b',
                        text:'250 m/s2'
                      },
                      {
                        id:'c',
                        text:'100 m/s2'
                      },
                      {
                        id:'d',
                        text:'10 m/s2'
                      },
                    ],
                    correct: 'c',
                    hintone: "Do you remember the formula of Newton's Second Law of Motion? What is the unit of mass? ",
                    hinttwo: "Force (N)  = Mass (kg) × Acceleration m / s2 . Mass is measured in kilograms(1 kilogram equals 1000gm)",
                    hintthree: ""
                  }
          ],
          "score":0,
          "current":1,
          "hintCounter":0
        }
    }



    setCurrent(current){
        this.setState({current});
    }

    setScore(score){
        this.setState({score});
    }

    setHintCounter(hintCounter){
        this.setState({hintCounter});
    }

    setShowHelp(showHelp){
        this.setState({showHelp});
    }

    setView(renderView){
        this.setState({renderView});
    }

    setPreTestStartTime(preTestStartTime){
        this.setState({preTestStartTime});
    }

    setPreTestAnswerDelay(preTestAnswerDelay){
        this.setState({preTestAnswerDelay});
    }

    setPreTestEndTime(preTestEndTime){
        this.setState({preTestEndTime});
    }

    componentDidMount() {


        // Needed to access "this" inside our callback
        const INSTANCE = this;

        // Connecting to our skill and subscribing to events
        Furhat(function (furhat) {

            // subscribes to DataDelivery event.
            furhat.subscribe('furhatos.app.furgui.DataDelivery', function (data) {
                INSTANCE.setState({
                    ...this.state,
                    buttons: data.buttons,
                    inputFields: data.inputFields
                })
            })

            // subscribes to ScreenDelivery event.
            furhat.subscribe('furhatos.app.furgui.ScreenDelivery', function (data) {
                INSTANCE.setState({
                    ...this.state,
                    screen: data.screen,
                })
            })

            // This event contains to data so we defined it inline in the flow
            furhat.subscribe('SpeechDone', function () {
                INSTANCE.setState({
                    ...this.state,
                    speaking: false
                })
            })

            // This event contains to data so we defined it inline in the flow
            furhat.subscribe('HelpText', function () {
                INSTANCE.setState({
                    ...this.state,
                    showHelp: true
                })
            })

            // Method that we can access outside of this callback, for sending
            INSTANCE.sendEvent = (data) => {
              furhat.send({
                event_name: data.event_name,
                data: data.data
              })
            }
        })
    }

    clickButton = (button) => {
        console.log("button clicked:" +button)

        this.setState({
            ...this.state,
            speaking: true,
            showHelp: false,
            renderView: button,
            preTestStartTime: currentDate,
            preTestAnswerDelay: currentDate

        })

        if(button == "Quiz"){
            var currentDate = new Date()
            this.restartQuiz()
        }

        this.sendEvent({
          event_name: "ClickButton",
          data: button
        })
    }

    variableSet = (variable, value) => {
        this.setState({
            ...this.state,
            speaking: true
        })
        this.sendEvent({
          event_name: "VariableSet",
          data: {
            variable,
            value
          }
        })
    }

    sendMessage = (message) => {
        console.log('Sending message to Tutor')
        this.setState({
            ...this.state,
            speaking: true

        })
        this.sendEvent({
          event_name: "Message",
          data: message
        })
    }

    sendNotification = (event_type, message) => {
        console.log('Sending message to Tutor')
        this.setState({
            ...this.state,
            speaking: true

        })
        this.sendEvent({
          event_name: "Notification",
          data: {
              event_type,
              message
          }
        })
    }

    restartQuiz = () => {
        console.log('Restarting Quiz')
        this.setCurrent(1)
        this.setScore(0)
    }

    render() {
      if(this.state.renderView == "Quiz"){
          console.log("RenderView: Quiz")

          if(this.state.current > this.state.questions.length){
                console.log("RenderView: Quiz result view")
                var results = <Results {...this.state} />;
                var progressbox='';
                var message = `You got, ${this.state.score}, correct answers out of, ${this.state.questions.length}, questions`;
                var scorebutton = <div><Button key="Check Score" label="Check Score" onClick={() => this.sendMessage(message)} speaking={this.state.speaking} style="info"/></div>;
          }
          else{
                var progressbox = <ProgressBox {...this.state} />
                var results='';

                const currentQuestion = this.state.questions.filter(question => question.id === this.state.current)

                if (this.state.showHelp){
                    if (this.state.hintCounter == 0){
                        var help = "Hint 1) "+currentQuestion[0].hintone
                    } else if (this.state.hintCounter == 1) {
                        var help = "Hint 1) "+currentQuestion[0].hintone + "***** Hint 2) "+ currentQuestion[0].hinttwo
                    } else {
                        var help = "Hint 1) "+currentQuestion[0].hintone + "***** Hint 2) "+ currentQuestion[0].hinttwo
                    }
                    console.log(help)
                    this.state.hintCounter = this.state.hintCounter+1
                    var helpBox = <HelpBox {...this.state} help={help}/>
                }
                var question = <QuestionList {...this.state} setCurrent={this.setCurrent.bind(this)} setScore={this.setScore.bind(this)} setShowHelp={this.setShowHelp.bind(this)} setHintCounter={this.setHintCounter.bind(this)} />;

          }

      } else if(this.state.renderView == "Lecture") {
                  var content = <Lecture {...this.state} />;
      } else if(this.state.renderView == "Pre-Test") {
            console.log("RenderView: Pre-Test")

              if(this.state.current > this.state.questions.length){
                    console.log("RenderView: Pre-Test result view")
                    var results = <Results {...this.state} />;
                    var progressbox='';
                    var message = `You got, ${this.state.score}, correct answers out of, ${this.state.questions.length}, questions`;
                    var scorebutton = <div><Button key="Check Score" label="Check Score" onClick={() => this.sendMessage(message)} speaking={this.state.speaking} style="info"/></div>;
              }
              else{
                    var progressbox = <ProgressBox {...this.state} />
                    var results='';

                    const currentQuestion = this.state.questions.filter(question => question.id === this.state.current)
                    var question = <QuestionList {...this.state} setCurrent={this.setCurrent.bind(this)} setScore={this.setScore.bind(this)} setShowHelp={this.setShowHelp.bind(this)} setHintCounter={this.setHintCounter.bind(this)}/>;

              }
      } else if(this.state.renderView == "Register") {
            var content = this.state.inputFields.map((label) =>
                <Input key={label} label={label} onSave={this.variableSet} speaking={this.state.speaking}/>)

      } else {
            var welcomeText = "Welcome to the Physics online tutorial"
      }


      return (
          <Grid>
            <Row className={"show-grid"} height="40">
                <Col sm={12}>
                    <Navbar bg="light">
                      <Navbar.Header>
                        <Navbar.Brand>
                          Social Robot Tutor
                        </Navbar.Brand>
                      </Navbar.Header>
                    </Navbar>
                </Col>
            </Row>
            <Row className={"show-grid"}>
                <Col sm={3}>
                    <ButtonGroup vertical>
                    <div className="well">
                    { this.state.buttons.map((label) =>
                        <Button key={label} label={label} onClick={this.clickButton} speaking={this.state.speaking} style="primary"/>
                    )}
                    </div>
                    </ButtonGroup>
                </Col>
                <Col sm={9}>
                    <div className="well">
                        {welcomeText}
                        {progressbox}
                        {question}
                        {results}
                        {content}
                        {scorebutton}

                    </div>
                    {helpBox}
                </Col>
             </Row>
             <Row className={"show-grid"}>
                <Col sm={12}>
                    <div className="well">
                    <h6>"Copyright 2020 Sethu Lekshmy"</h6>
                    </div>
                </Col>
            </Row>
          </Grid>
        )
    }

}

export default App;
