import java.util.Map;

import com.google.cloud.language.v1.AnalyzeEntitiesRequest;
import com.google.cloud.language.v1.AnalyzeEntitiesResponse;
import com.google.cloud.language.v1.AnalyzeSyntaxRequest;
import com.google.cloud.language.v1.AnalyzeSyntaxResponse;
import com.google.cloud.language.v1.Document;
import com.google.cloud.language.v1.Document.Type;
import com.google.cloud.language.v1.EncodingType;
import com.google.cloud.language.v1.Entity;
import com.google.cloud.language.v1.EntityMention;
import com.google.cloud.language.v1.LanguageServiceClient;
import com.google.cloud.language.v1.Sentiment;
import com.google.cloud.language.v1.Token;

public class gcloud {
	public static void main(String... args) throws Exception {
		// GoogleCredential credential = GoogleCredential.getApplicationDefault();
		// Instantiates a client
		try (LanguageServiceClient language = LanguageServiceClient.create()) {
			// The text to analyze
			String text = "Vai dar o cu";
			Document doc = Document.newBuilder().setContent(text).setType(Type.PLAIN_TEXT).build();

			// Detects the sentiment of the text
			Sentiment sentiment = language.analyzeSentiment(doc).getDocumentSentiment();

			AnalyzeEntitiesRequest request = AnalyzeEntitiesRequest.newBuilder().setDocument(doc)
					.setEncodingType(EncodingType.UTF16).build();

			AnalyzeEntitiesResponse response = language.analyzeEntities(request);

			// Print the response
			for (Entity entity : response.getEntitiesList()) {
				System.out.printf("Entity: %s\n", entity.getName());
				System.out.printf("Salience: %.3f\n", entity.getSalience());
				System.out.println("Metadata: ");
				for (Map.Entry<String, String> entry : entity.getMetadataMap().entrySet()) {
					System.out.printf("%s : %s", entry.getKey(), entry.getValue());
				}
				for (EntityMention mention : entity.getMentionsList()) {
					System.out.printf("Begin offset: %d\n", mention.getText().getBeginOffset());
					System.out.printf("Content: %s\n", mention.getText().getContent());
					System.out.printf("Type: %s\n\n", mention.getType());
				}
			}

			System.out.printf("Text: %s%n", text);
			System.out.printf("Sentiment: %s, %s%n", sentiment.getScore(), sentiment.getMagnitude());

			AnalyzeSyntaxRequest request2 = AnalyzeSyntaxRequest.newBuilder().setDocument(doc)
					.setEncodingType(EncodingType.UTF16).build();
			// analyze the syntax in the given text
			AnalyzeSyntaxResponse response2 = language.analyzeSyntax(request2);
			// print the response
			for (Token token : response2.getTokensList()) {
				System.out.printf("\tText: %s\n", token.getText().getContent());
				System.out.printf("\tBeginOffset: %d\n", token.getText().getBeginOffset());
				System.out.printf("Lemma: %s\n", token.getLemma());
				System.out.printf("PartOfSpeechTag: %s\n", token.getPartOfSpeech().getTag());
				System.out.printf("\tAspect: %s\n", token.getPartOfSpeech().getAspect());
				System.out.printf("\tCase: %s\n", token.getPartOfSpeech().getCase());
				System.out.printf("\tForm: %s\n", token.getPartOfSpeech().getForm());
				System.out.printf("\tGender: %s\n", token.getPartOfSpeech().getGender());
				System.out.printf("\tMood: %s\n", token.getPartOfSpeech().getMood());
				System.out.printf("\tNumber: %s\n", token.getPartOfSpeech().getNumber());
				System.out.printf("\tPerson: %s\n", token.getPartOfSpeech().getPerson());
				System.out.printf("\tProper: %s\n", token.getPartOfSpeech().getProper());
				System.out.printf("\tReciprocity: %s\n", token.getPartOfSpeech().getReciprocity());
				System.out.printf("\tTense: %s\n", token.getPartOfSpeech().getTense());
				System.out.printf("\tVoice: %s\n", token.getPartOfSpeech().getVoice());
				System.out.println("DependencyEdge");
				System.out.printf("\tHeadTokenIndex: %d\n", token.getDependencyEdge().getHeadTokenIndex());
				System.out.printf("\tLabel: %s\n\n", token.getDependencyEdge().getLabel());
			}
//			NAO FUNCIONA EM PORTUGUES, A ANALISE DE CATEGORIA DA FRASE!
//			ClassifyTextRequest request3 = ClassifyTextRequest.newBuilder().setDocument(doc).build();
//			// detect categories in the given text
//			ClassifyTextResponse response3 = language.classifyText(request3);
//
//			for (ClassificationCategory category : response3.getCategoriesList()) {
//				System.out.printf("Category name : %s, Confidence : %.3f\n", category.getName(),
//						category.getConfidence());
//			}
		}
	}
}
