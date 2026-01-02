package org.me.learning.questionmicros.controller;




import org.me.learning.questionmicros.model.Question;
import org.me.learning.questionmicros.model.QuestionWrapper;
import org.me.learning.questionmicros.model.Response;
import org.me.learning.questionmicros.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @Autowired
    Environment environment;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category){
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return  questionService.addQuestion(question);
    }

//    ! this service till here is running alone and it could be this way because microservices
//    can run independently from each other that mean if any service go down the other will stay
//    running 









    @GetMapping("generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz
            (@RequestParam String categoryName, @RequestParam Integer numQuestions ){
        return questionService.getQuestionsForQuiz(categoryName, numQuestions);
    }

    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds){
        System.out.println(environment.getProperty("local.server.port")); // to print the port number to know which instance we use and how it switch between them
        return questionService.getQuestionsFromId(questionIds);
    }

    @PostMapping("getScore")  // this will come from the quizService , the data will be send from there to get a score
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses)
    {
        return questionService.getScore(responses);
    }


    // generate
    // getQuestions (questionid)
    // getScore
//  !  admin portal

}