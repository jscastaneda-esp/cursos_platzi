# Python
from enum import Enum

# Pydantic
from pydantic import BaseModel, EmailStr, Field, PositiveInt, SecretStr

# FastAPI
from fastapi import Body, FastAPI, Path, Query

app = FastAPI()


# Models
class HairColor(Enum):
    white = "white"
    black = "black"
    brown = "brown"
    blonde = "blonde"
    red = "red"


class Person(BaseModel):
    id: PositiveInt | None = Field(
        None,
        title="Person ID",
        description="This is the person identifier. It's positive value"
    )
    first_name: str = Field(
        ...,
        min_length=1,
        max_length=50,
        title="Person Name",
        description="This is the person name. It's between 1 and 5 characters and it's required"
    )
    last_name: str = Field(
        ...,
        min_length=1,
        max_length=150,
        title="Person Last Name",
        description="This is the person last name. It's between 1 and 150 characters and it's required"
    )
    email: EmailStr = Field(
        ...,
        title="Person Email",
        description="This is the person email. It's required"
    )
    password: SecretStr = Field(
        ...,
        min_length=8,
        title="Person Password",
        description="This is the person password. It's minimun 8 characters required"
    )
    age: int = Field(
        ...,
        gt=0,
        le=115,
        title="Person Age",
        description="This is the person age. It's between 1 and 115 and it's required"
    )
    # In 3.6 < Python < 3.10, hair_color: typing.Optional[str] = None
    hair_color: HairColor | None = Field(
        None,
        title="Person Hair Color",
        description="This is the person hair color. It's black or blonde"
    )
    # In 3.6 < Python < 3.10, is_married: typing.Optional[bool] = None
    is_married: bool | None = Field(
        None,
        title="Person Is Married",
        description="This person is married"
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


@app.get("/")
def home():
    return {"Hello": "World"}


# Request and Response Body
@app.post("/person/new", response_model=Person)
def create_person(person: Person):
    return person


# Validations: Query parameters
@app.get("/person/detail")
def show_person(
    name: str | None = Query(
        None,
        min_length=1,
        max_length=50,
        title="Person Name",
        description="This is the person name. It's between 1 and 50 characters",
        example="Jonathan"
    ),
    age: int = Query(
        ...,
        gt=0,
        le=115,
        title="Person Age",
        description="This is the person age. It's between 1 and 115 and it's required",
        example=18
    )
):
    return {name, age}


# Validations: Path parameters
@app.get("/person/detail/{person_id}")
def show_person_by_id(person_id: int = Path(
    ...,
    gt=0,
    title="Person ID",
    description="This is the person identifier. It's greather that 0 and it's required",
    example=2
)):
    return {person_id: "It exists"}


# Validations: Request Body
@app.put("/person/{person_id}", response_model=Person)
def update_person(
    person_id: int = Path(
        ...,
        gt=0,
        title="Person ID",
        description="This is the person identifier. It's greather that 0 and it's required",
        example=20
    ),
    person: Person = Body(...)
):
    return person.dict() | {"id": person_id}
