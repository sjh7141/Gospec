package com.gospec.recommend;

import java.io.File;
import java.util.List;

import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;

public class UserRecommend {
	public static void main(String[] args) throws Exception {
//		DataModel model = new FileDataModel(new File("src/main/resources/test.csv"));
//		Collection<GenericItemSimilarity.ItemItemSimilarity> correlations = new ArrayList<GenericItemSimilarity.ItemItemSimilarity>();
//		ItemSimilarity itemSimilarity = new GenericItemSimilarity(correlations);
//		Recommender recommender = new GenericItemBasedRecommender(model, itemSimilarity);
//		Recommender cachingRecommender = new CachingRecommender(recommender);
//
//		List<RecommendedItem> recommendations = cachingRecommender.recommend(3, 3);
//
//		for (RecommendedItem recommendation : recommendations) {
//			System.out.println(recommendation);
//		}

		DataModel model = new FileDataModel(new File("src/main/resources/test.csv"));
//		TanimotoCoefficientSimilarity sim = new TanimotoCoefficientSimilarity(dm);
//		GenericItemBasedRecommender recommender = new GenericItemBasedRecommender(dm, sim);
//		int x = 1;
//		for (LongPrimitiveIterator items = dm.getItemIDs(); items.hasNext();) {
//			long itemId = items.nextLong();
//			List<RecommendedItem> recommendations = recommender.mostSimilarItems(itemId, 5);
//			for (RecommendedItem recommendation : recommendations) {
//				System.out.println(itemId + "," + recommendation.getItemID() + "," + recommendation.getValue());
//			}
//			x++;
//		}
		
		ItemSimilarity itemSimilarity = new EuclideanDistanceSimilarity (model);
		Recommender itemRecommender = new GenericItemBasedRecommender(model,itemSimilarity);
		List<RecommendedItem> itemRecommendations = itemRecommender.recommend(3, 2);
		for (RecommendedItem itemRecommendation : itemRecommendations) {
		System.out.println("Item: " + itemRecommendation);
		}
		

	}

}
