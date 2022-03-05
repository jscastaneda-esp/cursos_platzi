# Python
from datetime import (
    date,
    datetime
)
from uuid import UUID

# Pydantic
from pydantic import (
    BaseModel,
    EmailStr,
    Field
)

# FastAPI
from fastapi import (
    FastAPI,
    status
)

app = FastAPI()


# Models

# - Users
class BaseUser(BaseModel):
    user_id: UUID = Field(...)
    email: EmailStr = Field(...)


class UserLogin(BaseUser):
    password: str = Field(
        default=...,
        min_length=8,
        max_length=64
    )


class User(BaseUser):
    first_name: str = Field(
        default=...,
        min_length=1,
        max_length=50
    )
    last_name: str = Field(
        default=...,
        min_length=1,
        max_length=50
    )
    birth_date: date | None = Field(None)


# - Tweets
class Tweet(BaseModel):
    tweet_id: UUID = Field(...)
    content: str = Field(
        default=...,
        min_length=1,
        max_length=256
    )
    created_at: datetime = Field(datetime.now())
    updated_at: datetime | None = Field(None)
    by: User = Field(...)


# Path Operations
@app.get("/")
def home():
    return {"Twitter API": "Working!"}


# - Users
@app.post(
    path="/signup",
    response_model=User,
    status_code=status.HTTP_201_CREATED,
    summary="Register a User",
    tags=["Users"]
)
def signup():
    pass


@app.post(
    path="/login",
    response_model=User,
    status_code=status.HTTP_200_OK,
    summary="Login a User",
    tags=["Users"]
)
def login():
    pass


@app.get(
    path="/users",
    response_model=list[User],
    status_code=status.HTTP_200_OK,
    summary="Show all Users",
    tags=["Users"]
)
def show_users():
    pass


@app.get(
    path="/users/{user_id}",
    response_model=User,
    status_code=status.HTTP_200_OK,
    summary="Show a User",
    tags=["Users"]
)
def show_user():
    pass


@app.put(
    path="/users/{user_id}/update",
    response_model=User,
    status_code=status.HTTP_200_OK,
    summary="Update a User",
    tags=["Users"]
)
def update_user():
    pass


@app.delete(
    path="/users/{user_id}/delete",
    response_model=User,
    status_code=status.HTTP_200_OK,
    summary="Delete a User",
    tags=["Users"]
)
def delete_user():
    pass


# - Tweets
