import 'package:flutter/material.dart';
import 'tree.dart';

class TreeList extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    return Container(
      margin: EdgeInsets.only(
        top: 100
      ),
      child: ListView(
        children: [
          Tree(
            pathPhoto: 'images/adansonia_digitata.jpg',
            name: 'Adansonia Digitata',
            description: 'Adansonia digitata, el baobab africano o árbol del pan del mono, ​ es el nombre de un árbol africano de la familia de las malváceas.',
          ),
          Container(
            decoration: BoxDecoration(
              border: Border.all(color: Colors.grey, width: 0.2)
            ),
          ),
          Tree(
            pathPhoto: 'images/drago.jpg',
            name: 'Drago',
            description: 'Dracaena draco, el drago, ​ es una especie de planta arbórea típica del clima subtropical de Macaronesia, particularmente de las Islas Canarias, pero cuya mayor población se encuentra en el oeste de Marruecos.',
          ),
          Container(
            decoration: BoxDecoration(
                border: Border.all(color: Colors.grey, width: 0.2)
            ),
          ),
          Tree(
            pathPhoto: 'images/fresno.jpg',
            name: 'Fresno norteño',
            description: 'Fraxinus excelsior, el fresno norteño, ​ fresno de hoja ancha o fresno común, es una especie perteneciente a la familia de las oleáceas. Es un árbol nativo de la mayor parte de Europa; desde España, principalmente el norte, ​ hasta Rusia.',
          ),
          Container(
            decoration: BoxDecoration(
                border: Border.all(color: Colors.grey, width: 0.2)
            ),
          ),
          Tree(
            pathPhoto: 'images/ginkgo_biloba.jpg',
            name: 'Ginkgo Biloba',
            description: 'Ginkgo biloba, gingko, árbol de los cuarenta escudos o nogal del Japón​ es un árbol único en el mundo, sin parientes vivos. Está muchas veces clasificado en su propia división, Ginkgophyta, siendo el único miembro de la clase Ginkgopsida, orden Ginkgoales, familia Ginkgoaceae, género Ginkgo.',
          ),
          Container(
            decoration: BoxDecoration(
                border: Border.all(color: Colors.grey, width: 0.2)
            ),
          ),
          Tree(
            pathPhoto: 'images/platanus_occidentalis.jpg',
            name: 'Platanus Occidentalis',
            description: 'Platanus occidentalis, el plátano occidental, plátano de Virginia o sicomoro americano, es una de las especies de Platanus nativa de Norteamérica.',
          ),
          Container(
            decoration: BoxDecoration(
                border: Border.all(color: Colors.grey, width: 0.2)
            ),
          ),
          Tree(
            pathPhoto: 'images/sauce_lloron.jpg',
            name: 'Sauce Llorón',
            description: 'Salix babylonica o sauce llorón, es un árbol que pertenece a la familia de las salicáceas y es nativo del este de Asia.​​',
          ),
          Container(
            decoration: BoxDecoration(
                border: Border.all(color: Colors.grey, width: 0.2)
            ),
          ),
          Tree(
            pathPhoto: 'images/secuoya_gigante.jpg',
            name: 'Secuoya Gigante',
            description: 'Sequoiadendron giganteum es la única especie de Sequoiadendron, un género monotípico de árboles perteneciente a la familia de las Cupresáceas, también llamadas Taxodiáceas. Es conocida como secuoya, secoya gigante, velintonia, wellingtonia, secoya de Sierra o gran árbol.',
          ),
          Container(
            decoration: BoxDecoration(
                border: Border.all(color: Colors.grey, width: 0.2)
            ),
          ),
          Tree(
            pathPhoto: 'images/roble_comun.jpg',
            name: 'Roble Común',
            description: 'Quercus robur, roble común, ​ roble carballo, cajiga o roble fresnal es un árbol robusto, de porte majestuoso, que puede superar los 40 m de altura. Está clasificado en la Sección Quercus, que son los robles blancos de Europa, Asia y América del Norte.',
          ),
          Container(
            margin: EdgeInsets.only(
                bottom: 10
            ),
          ),
        ],
      ),
    );
  }
}