package com.praxis;

import com.praxis.model.Experiment;
import com.praxis.model.Question;
import com.praxis.model.QuestionOption;
import com.praxis.model.QuestionType;
import com.praxis.repository.ExperimentRepository;
import com.praxis.repository.QuestionOptionRepository;
import com.praxis.repository.QuestionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class PraxisApplication {

	public static void main(String[] args) {
		SpringApplication.run(PraxisApplication.class, args);
	}

	@Bean
	CommandLineRunner initData(ExperimentRepository experimentRepository,
	                           QuestionRepository questionRepository,
	                           QuestionOptionRepository questionOptionRepository) {
		return args -> {

			// CRT
			if (!experimentRepository.existsByName("Cognitive Reflection Test")) {
				Experiment crt = new Experiment("Cognitive Reflection Test", "The CRT was designed to assess a specific cognitive ability. It assesses individuals' ability to suppress an intuitive and spontaneous (\"system 1\") wrong answer in favor of a reflective and deliberative (\"system 2\") right answer.", true);
				experimentRepository.save(crt);

				questionRepository.save(new Question(crt, "A bat and a ball cost $1.10 in total. The bat costs $1.00 more than the ball. How much does the ball cost?", "0.10", "0.05", QuestionType.FREE_TEXT, "The intuitive answer is $0.10, but if the ball costs $0.10, the bat would cost $1.10, making the total $1.20. The correct answer is $0.05."));
				questionRepository.save(new Question(crt, "If it takes 5 machines 5 minutes to make 5 widgets, how long would it take 100 machines to make 100 widgets?", "100", "5", QuestionType.FREE_TEXT, "Each machine takes 5 minutes to make one widget. 100 machines working in parallel still take 5 minutes to make 100 widgets."));
				questionRepository.save(new Question(crt, "In a lake, there is a patch of lily pads. Every day, the patch doubles in size. If it takes 48 days for the patch to cover the entire lake, how long would it take for the patch to cover half of the lake?", "24", "47", QuestionType.FREE_TEXT, "Because the patch doubles every day, on day 47 it covers half the lake — one doubling away from full coverage on day 48."));
			}

			// Asian Disease Problem
			if (!experimentRepository.existsByName("Asian Disease Problem")) {
				Experiment adp = new Experiment("Asian Disease Problem", "A classic experiment by Kahneman & Tversky demonstrating the framing effect: identical outcomes described as gains or losses produce systematically different choices.", false);
				experimentRepository.save(adp);

				Question q1 = new Question(adp, "Imagine that the U.S. is preparing for the outbreak of an unusual Asian disease, which is expected to kill 600 people. Two alternative programs to combat the disease have been proposed. If Program A is adopted, 200 people will be saved. If Program B is adopted, there is a 1/3 probability that 600 people will be saved, and 2/3 probability that no people will be saved. Which program do you choose?", null, "Program A", QuestionType.SINGLE_CHOICE, "This is the gain frame. Most people choose Program A (certainty). Kahneman & Tversky found ~72% chose A. When outcomes are framed as gains, people tend to be risk-averse.");
				questionRepository.save(q1);
				questionOptionRepository.save(new QuestionOption("Program A", q1));
				questionOptionRepository.save(new QuestionOption("Program B", q1));

				Question q2 = new Question(adp, "If Program C is adopted, 400 people will die. If Program D is adopted, there is a 1/3 probability that nobody will die, and 2/3 probability that 600 people will die. Which program do you choose?", null, "Program C", QuestionType.SINGLE_CHOICE, "This is the loss frame — mathematically identical to Q1. Most people switch to Program D (risk-seeking). Kahneman & Tversky found ~78% chose D. The framing effect: identical outcomes, opposite choices.");
				questionRepository.save(q2);
				questionOptionRepository.save(new QuestionOption("Program C", q2));
				questionOptionRepository.save(new QuestionOption("Program D", q2));
			}

			// Ultimatum Game
			if (!experimentRepository.existsByName("Ultimatum Game")) {
				Experiment ug = new Experiment("Ultimatum Game", "A classic behavioral economics experiment testing fairness and rationality. One player proposes how to split a sum of money; the other accepts or rejects. Rejection means both get nothing.", false);
				experimentRepository.save(ug);

				questionRepository.save(new Question(ug, "You have been given $10 to split with another participant. You propose how much to give them. If they reject your offer, both of you receive nothing. How much do you offer?", null, null, QuestionType.SLIDER, 0, 10, 1, "Purely rational actors should offer the minimum ($1) and accept any offer above $0. In practice, offers below $3 are frequently rejected — people punish unfairness even at personal cost. The modal offer is $5, reflecting a fairness norm."));
			}
		};
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/**")
						.allowedOrigins("http://localhost:5173")
						.allowedMethods("GET", "POST", "PUT", "DELETE");
			}
		};
	}

}
