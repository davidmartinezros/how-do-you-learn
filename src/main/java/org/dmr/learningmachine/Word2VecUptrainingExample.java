package org.dmr.learningmachine;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;

import org.deeplearning4j.models.embeddings.WeightLookupTable;
import org.deeplearning4j.models.embeddings.inmemory.InMemoryLookupTable;
import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.word2vec.VocabWord;
import org.deeplearning4j.models.word2vec.Word2Vec;
import org.deeplearning4j.models.word2vec.wordstore.inmemory.InMemoryLookupCache;
import org.deeplearning4j.text.sentenceiterator.BasicLineIterator;
import org.deeplearning4j.text.sentenceiterator.SentenceIterator;
import org.deeplearning4j.text.tokenization.tokenizer.preprocessor.CommonPreprocessor;
import org.deeplearning4j.text.tokenization.tokenizerfactory.DefaultTokenizerFactory;
import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is simple example for model weights update after initial vocab building.
 * If you have built your w2v model, and some time later you've decided that it can be
 * additionally trained over new corpus, here's an example how to do it.
 *
 * PLEASE NOTE: At this moment, no new words will be added to vocabulary/model.
 * Only weights update process will be issued. It's often called "frozen vocab training".
 *
 * @author raver119@gmail.com
 */
public class Word2VecUptrainingExample {

    private static Logger log = LoggerFactory.getLogger(Word2VecUptrainingExample.class);
    
    public static SentenceIterator defineSentenceItetator(String filePath) throws FileNotFoundException {
    	/*
    	 * Strip white space before and after for each line
    	 */
    	log.info("defineSenteceItetator");
    	
        return new BasicLineIterator(new File(filePath));
    }
    
    public static TokenizerFactory defineTokenizerFactory() {
    	/*
    	 * Split on white spaces in the line to get words
    	 */
    	log.info("defineTokenizerFactory");
    	
        TokenizerFactory t = new DefaultTokenizerFactory();
        t.setTokenPreProcessor(new CommonPreprocessor());
        
        return t;
    }
    
    public static Word2Vec createModel(SentenceIterator iter, TokenizerFactory t) {
    	// manual creation of VocabCache and WeightLookupTable usually isn't necessary
        // but in this case we'll need them
        InMemoryLookupCache cache = new InMemoryLookupCache();
        WeightLookupTable<VocabWord> table = new InMemoryLookupTable.Builder<VocabWord>()
                .vectorLength(100)
                .useAdaGrad(false)
                .cache(cache)
                .lr(0.025f).build();

        log.info("Building model....");
        Word2Vec vec = new Word2Vec.Builder()
                .minWordFrequency(5)
                .iterations(1)
                .epochs(1)
                .layerSize(100)
                .seed(42)
                .windowSize(5)
                .iterate(iter)
                .tokenizerFactory(t)
                .lookupTable(table)
                .vocabCache(cache)
                .build();
        
        return vec;
    }
    
    public static void prepareForTraining(Word2Vec vec) {
    	
    	log.info("Fitting Word2Vec model....");
        vec.fit();
    }
    
    public static Collection<String> getWordsNearest(Word2Vec vec, String word, int amount) {
    	
    	Collection<String> lst = vec.wordsNearest(word, amount);
        log.info("Closest words to" + word + " on 1st run: " + lst);
        
        return lst;
    }
    
    public static void writeFullModel(Word2Vec vec, String pathFileToSave) {
    	
    	/*
	     * at this moment we're supposed to have model built, and it can be saved for future use.
	     */
	    WordVectorSerializer.writeFullModel(vec, pathFileToSave);
    
    }
    
    public static Word2Vec loadFullModel(String pathFileToSave) throws FileNotFoundException {
    	
    	/*
	     * Let's assume that some time passed, and now we have new corpus to be used to weights update.
	     * Instead of building new model over joint corpus, we can use weights update mode.
	     */
	    return WordVectorSerializer.loadFullModel(pathFileToSave);
    }
    
    public static Collection<String> execute(String word, String theme) throws Exception {

    	//(p.ex. "LM_FILE_" + TEMA_VERSIO_DATA(dd_MM_yyyy).txt)
    	//(p.ex. "TRAIN_FILE_" + TEMA_VERSIO_DATA(dd_MM_yyyy).txt)
    	
    	String filePathPrasesTheme = "/deeplearning4j/" + theme + "_PHRASES_FILE.txt";
    	String filePathSavedModel = "/deeplearning4j/" + theme + "_TRAIN_FILE.txt";
    	
    	// Carreguem
        Word2Vec word2Vec = loadFullModel(filePathSavedModel);

        /*
         * PLEASE NOTE: after model is restored, it's still required to set SentenceIterator and TokenizerFactory, if you're going to train this model
         */
        log.info("Load & Vectorize Sentences....");
        SentenceIterator iter2 = defineSentenceItetator(filePathPrasesTheme);
        TokenizerFactory t2    = defineTokenizerFactory();

        word2Vec.setTokenizerFactory(t2);
        word2Vec.setSentenceIterator(iter2);

        Collection<String> lst = getWordsNearest(word2Vec, word, 10);
        
        return lst;
    }
    
    public static Collection<String> train(String word, String theme) throws Exception {
    	
    	//(p.ex. "LM_FILE_" + TEMA_VERSIO_DATA(dd_MM_yyyy).txt)
    	//(p.ex. "TRAIN_FILE_" + TEMA_VERSIO_DATA(dd_MM_yyyy).txt)

    	String filePathPrasesTheme = "/deeplearning4j/" + theme + "_PHRASES_FILE.txt";
    	String filePathSavedModel = "/deeplearning4j/" + theme + "_TRAIN_FILE.txt";
    	
        log.info("Load & Vectorize Sentences....");
        SentenceIterator iter = defineSentenceItetator(filePathPrasesTheme);
        TokenizerFactory t    = defineTokenizerFactory();

        Word2Vec vec = createModel(iter,t);

        prepareForTraining(vec);

        Collection<String> lst = getWordsNearest(vec, word, 10);
        
        // Grabem
        writeFullModel(vec, filePathSavedModel);
        
        return lst;           
    }
}