# ABAC

## Property
- role
- module
- action

## Role
- Root
- - Add Admin
- - Delete Admin
- Admin
- - Delete all things(Including User)
- - Create Tag
- User
- - Create All Things But Tag
- - Delete Own Things

# Sample Rules
```kotlin
rules{
    Policy{
        role = "Root"
        module = "Admin"
        action = "Add"
        strategy = "Allow"
    }
    Policy{
        role = "Admin"
        module = "User"
        action = "create,delete,update,query"
    }
}
```
