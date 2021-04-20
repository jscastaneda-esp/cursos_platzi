import 'package:flutter/material.dart';

class Tree extends StatelessWidget {

  Tree({
    Key key,
    this.pathPhoto,
    this.name,
    this.description
  }): super(key: key);

  final String pathPhoto;
  final String name;
  final String description;

  @override
  Widget build(BuildContext context) {
    final photo = Container(
      margin: EdgeInsets.only(
        left: 20
      ),
      width: 70,
      height: 70,
      decoration: BoxDecoration(
        shape: BoxShape.circle,
        image: DecorationImage(
          fit: BoxFit.cover,
          image: AssetImage(pathPhoto)
        )
      ),
    );

    return Container(
      padding: EdgeInsets.only(
        top: 10,
        bottom: 10
      ),
      child: Row(
        children: [
          photo,
          TreeDetails(
            name: name,
            description: description,
          ),
          ButtonFavorite()
        ],
      ),
    );
  }
}

class TreeDetails extends StatelessWidget {

  TreeDetails({
    Key key,
    this.name,
    this.description
  }): super(key: key);

  final String name;
  final String description;

  @override
  Widget build(BuildContext context) {
    final width = MediaQuery.of(context).size.width-190;

    final nameTree = Container(
      margin: EdgeInsets.only(
        left: 20,
      ),
      width: width,
      child: Text(
        name,
        textAlign: TextAlign.left,
        style: TextStyle(
          fontWeight: FontWeight.w500,
          fontSize: 17
        ),
      ),
    );

    final descriptionTree = Container(
      margin: EdgeInsets.only(
        top: 5,
        right: 20,
        left: 20
      ),
      width: width,
      height: 40,
      child: SingleChildScrollView(
        scrollDirection: Axis.vertical,
        child: Text(
          description,
          textAlign: TextAlign.justify,
          style: TextStyle(
            fontSize: 13,
            color: Color(0xffa3a5a7),
          ),
        ),
      ),
    );

    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        nameTree,
        descriptionTree
      ],
    );
  }
}

class ButtonFavorite extends StatefulWidget {

  @override
  State<StatefulWidget> createState() {
    return _ButtonFavorite();
  }
}

class _ButtonFavorite extends State<ButtonFavorite> {

  bool _isFavorited = false;

  void onPressedFav() {
    Scaffold.of(context).showSnackBar(
        SnackBar(
          duration: Duration(
              milliseconds: 750
          ),
          content: Text(_isFavorited ? 'Eliminado de tus favoritos' : 'Agregado a tus favoritos'),
        )
    );

    setState(() => _isFavorited = !_isFavorited);
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      margin: EdgeInsets.only(
        top: 20,
        right: 20
      ),
      height: 40,
      width: 40,
      child: IconButton(
        splashColor: Colors.grey,
        splashRadius: 30,
        color: Colors.lightGreen,
        tooltip: 'Fav',
        onPressed: onPressedFav,
        icon: Icon(
            _isFavorited ? Icons.favorite : Icons.favorite_border
        ),
      ),
    );
  }
}