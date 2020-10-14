package com.cos.crawling.service;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cos.crawling.model.Movie;
import com.cos.crawling.repository.MovieRepository;

@Service
public class CrawlingService{
	
	@Autowired
	private MovieRepository movieRepository;

	public void crawling() {
		List<Movie> movies = new ArrayList<>();
		String url="https://movie.naver.com/movie/running/current.nhn";
		Document doc=null;
		try {
			doc=Jsoup.connect(url).get();
			Elements movieList=doc.select("ul.lst_detail_t1").select("li");
			for(int i=0; i<movieList.size();i++) {
				Element movie = movieList.get(i);
				String title=movie.select(".tit").select("a").text();
				String score=movie.select(".star_t1").select(".num").text().split(" ")[0];
				//String[] str=score.split(" ");
				String level=movie.select(".tit").select("span").text();
				
				Elements elements=movie.select(".info_txt1").select("dd");
				
				String info=null,director=null, actor=null;
				info=elements.get(0).text().replace("|", "-");
				director=elements.get(1).text();
				
				if(elements.size()==3) {
					actor=elements.get(2).text();
				}
				String strImageUrl=movie.select(".thumb").select("img").attr("src");
				String strImageName=strImageUrl.substring(strImageUrl.lastIndexOf("/")+1, strImageUrl.indexOf("?"));
				String ext=strImageName.substring(strImageName.indexOf("."));
				strImageName=strImageName.substring(0, strImageName.indexOf("."));
				strImageName=strImageName+i+ext;
				
				movies.add(Movie.builder()
						.id(i)
				.title(title)
				.score(score)
				.level(level)
				.info(info)
				.director(director)
				.actor(actor).build());
				
			}
		}catch(Exception e) {
			System.out.println("크롤링에러");
		}
		movieRepository.saveAll(movies);
	}
	
	public List<Movie> 영화목록(){
		return movieRepository.findAll();
	}
	
}
