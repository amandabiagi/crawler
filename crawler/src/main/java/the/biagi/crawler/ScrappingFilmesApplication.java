package the.biagi.crawler;

import java.io.IOException;

public class ScrappingFilmesApplication {

    public static void main(String[] args) throws IOException {

        ScrappingService scrappingService = new ScrappingService();

        //System.out.println(scrappingService.urls());
       //System.out.println(scrappingService.urlsTop10());
     scrappingService.listaTopFilmes();
      //scrappingService.nomesDiretores("https://www.imdb.com/title/tt0270846/?pf_rd_m=A2FGELUUNOQJNL&pf_rd_p=c28fd853-7526-417a-ad2b-02e76562a472&pf_rd_r=RWAG5N4P5HKG9D22707G&pf_rd_s=center-1&pf_rd_t=15506&pf_rd_i=bottom&ref_=chtbtm_tt_2");
      //scrappingService.nomesElencoPrincipal("https://www.imdb.com/title/tt0270846/?pf_rd_m=A2FGELUUNOQJNL&pf_rd_p=c28fd853-7526-417a-ad2b-02e76562a472&pf_rd_r=RWAG5N4P5HKG9D22707G&pf_rd_s=center-1&pf_rd_t=15506&pf_rd_i=bottom&ref_=chtbtm_tt_2");

       // scrappingService.comentarioPositivo("https://www.imdb.com/title/tt0270846/?pf_rd_m=A2FGELUUNOQJNL&pf_rd_p=c28fd853-7526-417a-ad2b-02e76562a472&pf_rd_r=RWAG5N4P5HKG9D22707G&pf_rd_s=center-1&pf_rd_t=15506&pf_rd_i=bottom&ref_=chtbtm_tt_2");
    }

}
