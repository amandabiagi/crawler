import React, {useState, useEffect} from "react";
import Tabela from "./componentes-menores/Tabela";
import axios from "axios";



function Home(){
    const [filmes, setFilmes] = useState([]);
    useEffect(() => {
        try {
          axios.get('http://localhost:8080/filmes').then((resposta) =>{
                setFilmes(resposta.data)
          });  
        } catch (erro) {
            console.log(erro);
        }
       
    }, []);

    return(
        <>
        <h1 className = "titulo">OS 10 PIORES FILMES</h1>
        <Tabela obj={filmes}/> 
        </>
    );
}

export default Home;