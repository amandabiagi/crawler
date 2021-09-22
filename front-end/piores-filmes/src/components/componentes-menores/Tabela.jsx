import React from "react";
import "./tabela.css"

export default(props) =>{

    const filmes = props.obj;

function tabela(){
 return filmes.map((filme, i) => {
     return(
         <tr className = "trValor">
             <td>{++i + "º"}</td>
             <td>{filme.nomeFilme}</td>
             <td>{filme.nota.toFixed(2)}</td>
             <td>{filme.direcao}</td>
             <td>{filme.elencoPrincipal}</td>
             <td>{filme.comentarioPositivo}</td>
         </tr>
         
     )
 }) 

}  
    return(
        <div>
            <table className = 'tabelaPrincipal'>
                <thead>
                    <tr>
                        <th>POSIÇÃO</th>        
                        <th>NOME DO FILME</th>
                        <th>AVALIAÇÃO</th>
                        <th>DIRETOR</th>
                        <th>ELENCO PRINCIPAL</th>
                        <th>COMENTARIO POSITIVO</th>
                    </tr>
                </thead>
                <tbody>{tabela()}</tbody>
            </table>
        </div>
    );
}
