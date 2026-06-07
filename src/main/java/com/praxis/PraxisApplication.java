package com.praxis;

import com.praxis.model.Experiment;
import com.praxis.model.Question;
import com.praxis.repository.ExperimentRepository;
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
                               QuestionRepository questionRepository) {
		return args -> {
			if (experimentRepository.count() == 0) {
				Experiment crt = new Experiment("Cognitive Reflection Test", "The CRT was designed to assess a specific cognitive ability. It assesses individuals' ability to suppress an intuitive and spontaneous (\"system 1\") wrong answer in favor of a reflective and deliberative (\"system 2\") right answer.");
				experimentRepository.save(crt);

				Question question1 = new Question(
						crt,
						"A bat and a ball cost $1.10 in total. The bat costs $1.00 more than the ball. \"How much does the ball cost?\"",
						"0.10",
						"0.05"
				);

				Question question2 = new Question(
						crt,
						"If it takes 5 machines 5 minutes to make 5 widgets, how long would it take\n" +
								"100 machines to make 100 widgets?",
						"100",
						"5"
				);

				Question question3 = new Question(
						crt,
						"In a lake, there is a patch of lily pads. Every day, the patch doubles in size.\n" +
								"If it takes 48 days for the patch to cover the entire lake, how long would it\n" +
								"take for the patch to cover half of the lake?",
						"24",
						"47"
				);

				questionRepository.save(question1);
				questionRepository.save(question2);
				questionRepository.save(question3);
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
