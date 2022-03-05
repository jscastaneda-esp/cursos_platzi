# Python
from enum import Enum

# Pydantic
from pydantic import (
    BaseModel,
    EmailStr,
    Field,
    PositiveInt,
    SecretStr
)

# FastAPI
from fastapi import (
    Body,
    Cookie,
    FastAPI,
    File,
    Form,
    Header,
    Path,
    Query,
    UploadFile,
    status,
    HTTPException
)

app = FastAPI()


# Models
class HairColor(Enum):
    white = "white"
    black = "black"
    brown = "brown"
    blonde = "blonde"
    red = "red"


class BasePerson(BaseModel):
    first_name: str = Field(
        default=...,
        min_length=1,
        max_length=50,
        title="Person Name",
        description="This is the person name. It's between 1 and 5 characters and it's required"
    )
    last_name: str = Field(
        default=...,
        min_length=1,
        max_length=150,
        title="Person Last Name",
        description="This is the person last name. It's between 1 and 150 characters and it's required"
    )
    email: EmailStr = Field(
        default=...,
        title="Person Email",
        description="This is the person email. It's required"
    )
    age: int = Field(
        default=...,
        gt=0,
        le=115,
        title="Person Age",
        description="This is the person age. It's between 1 and 115 and it's required"
    )
    # In 3.6 < Python < 3.10, hair_color: typing.Optional[str] = None
    hair_color: HairColor | None = Field(
        default=None,
        title="Person Hair Color",
        description="This is the person hair color. It's black or blonde"
    )
    # In 3.6 < Python < 3.10, is_married: typing.Optional[bool] = None
    is_married: bool | None = Field(
        default=None,
        title="Person Is Married",
        description="This person is married"
    )


class Person(BasePerson):
    password: SecretStr = Field(
        default=...,
        min_length=8,
        title="Person Password",
        description="This is the person password. It's minimun 8 characters required"
    )

    class Config:
        schema_extra = {
            "example": {
                "first_name": "Jonathan",
                "last_name": "Castañeda Espinosa",
                "email": "jcaatanedaesp@gmail.com",
                "password": "C0nTr4s3n1A",
                "age": 27,
                "hair_color": "black",
                "is_married": False
            }
        }


class PersonOut(BasePerson):
    id: PositiveInt = Field(
        default=...,
        title="Person ID",
        description="This is the person identifier. It's positive value and it's required"
    )

    class Config:
        schema_extra = {
            "example": {
                "id": 10,
                "first_name": "Jonathan",
                "last_name": "Castañeda Espinosa",
                "email": "jcaatanedaesp@gmail.com",
                "age": 27,
                "hair_color": "black",
                "is_married": False
            }
        }


class LoginOut(BaseModel):
    username: str = Field(
        default=...,
        max_length=20,
        description="This is username the person. It's required",
        example="jscastaneda"
    )
    message: str = Field(
        default="Login Successfully!",
        description="This is message result operation"
    )


# Path Operations function
@app.get(
    path="/",
    status_code=status.HTTP_200_OK,
    tags=["Home"],
    summary="Show version app"
)
def version():
    """
    Version app

    This path operation show version app

    Returns a version app
    """

    return "v1"


# Request and Response Body
@app.post(
    path="/person/new",
    response_model=PersonOut,
    status_code=status.HTTP_201_CREATED,
    tags=["Persons"],
    summary="Create person in the app"
)
def create_person(person: Person = Body(...)):
    """
    Create Person

    This path operation creates a person in the app and save the information in the database

    Parameters:
    - Request body parameter:
        - **person: Person** -> A person model with data to save

    Returns a person model with data saved
    """

    return person


# Validations: Query parameters
@app.get(
    path="/person/detail",
    status_code=status.HTTP_200_OK,
    tags=["Persons"],
    summary="Show details person saved in the app"
)
def show_person(
    name: str | None = Query(
        default=None,
        min_length=1,
        max_length=50,
        title="Person Name",
        description="This is the person name. It's between 1 and 50 characters",
        example="Jonathan"
    ),
    age: int = Query(
        default=...,
        gt=0,
        le=115,
        title="Person Age",
        description="This is the person age. It's between 1 and 115 and it's required",
        example=18
    )
):
    """
    Show Details Person

    This path operation show details person data saved in the database

    Parameters:
    - Query parameters:
        - **name: String** -> A name for search a person
        - **age: Integer** -> An age for search a person

    Returns a person model found
    """

    return {name, age}


# Validations: Path parameters
persons = [1, 2, 3, 4, 5]


@app.get(
    path="/person/detail/{person_id}",
    status_code=status.HTTP_200_OK,
    tags=["Persons"],
    responses={
        404: {
            "content": {
                "application/json": {
                    "example": {"detail": "Resource not found"}
                }
            }
        }
    },
    summary="Show details person saved by id in the app"
)
def show_person_by_id(person_id: int = Path(
    default=...,
    gt=0,
    title="Person ID",
    description="This is the person identifier. It's greather that 0 and it's required",
    example=2
)):
    """
    Show Details Person By ID

    This path operation show details person data saved in the database

    Parameters:
    - Path parameters:
        - **person_id: Integer** -> A ID for search a person

    Returns a person model found
    """

    if person_id not in persons:
        raise HTTPException(
            status_code=status.HTTP_404_NOT_FOUND,
            detail="¡This person doesn't exists!"
        )

    return {person_id: "It exists!"}

# Validations: Request Body


@app.put(
    path="/person/{person_id}",
    response_model=PersonOut,
    status_code=status.HTTP_201_CREATED,
    tags=["Persons"],
    summary="Update person in the app"
)
def update_person(
    person_id: int = Path(
        default=...,
        gt=0,
        title="Person ID",
        description="This is the person identifier. It's greather that 0 and it's required",
        example=20
    ),
    person: Person = Body(...)
):
    """
    Update Person

    This path operation updates a person data in the database

    Parameters:
    - Path parameters:
        - **person_id: Integer** -> A ID for search a person
    - Request body parameter:
        - **person: Person** -> A person model with data to save

    Returns a person model with data saved
    """

    return person.dict() | {"id": person_id}


@app.post(
    path="/login",
    response_model=LoginOut,
    status_code=status.HTTP_200_OK,
    tags=["Persons"],
    summary="SignIn person in the app"
)
def login(
    username: str = Form(...),
    password: str = Form(...)
):
    """
    SignIn Person

    This path operation authenticate person in the app and validate credentials saved in the database

    Parameters:
    - Form parameters:
        - **username: String** -> A username for authenticate in the app
        - **password: String** -> A password for authenticate in the app

    Returns a result message the authentication
    """

    return LoginOut(username=username)


# Cookies and headers parameters
@app.post(
    path="/contact",
    status_code=status.HTTP_200_OK,
    tags=["Support"],
    summary="Contact with support the app"
)
def contact(
    first_name: str = Form(
        default=...,
        min_length=1,
        max_length=20
    ),
    last_name: str = Form(
        default=...,
        min_length=1,
        max_length=100
    ),
    email: EmailStr = Form(...),
    message: str = Form(
        default=...,
        min_length=20,
        max_length=2000
    ),
    user_agent: str | None = Header(None),
    ads: str | None = Cookie(None)
):
    """
    Contact with Support

    This path operation contacts with support the app and solution problems

    Parameters:
    - Form parameters:
        - **first_name: String** -> The first name person that request support in the app
        - **last_name: String** -> The last name person that request support in the app
        - **email: String** -> The person contact email that request support in the app
        - **message: String** -> A detail the request support in the app

    - Header parameters:
        - **user_agent: String** -> A header for identified the type client

    - Cookie parameters:
        - **ads: String** -> A message sended from client app

    Returns the user agent sended from client app
    """

    return user_agent


# Files
@app.post(
    path="/post-image",
    tags=["Files"],
    summary="Upload Image in the app"
)
def post_image(
    image: UploadFile = File(...)
):
    """
    Upload Image

    This path operation allow uploads images in the app

    Parameters:
    - Multipart parameters:
        - **image: File** -> A image from upload in the app

    Returns info the uploaded image
    """

    return {
        "Filename": image.filename,
        "Format": image.content_type,
        "Size(kb)": round(len(image.file.read()) / 1024, ndigits=2)
    }


#
