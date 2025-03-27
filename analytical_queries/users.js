db.ratings.aggregate([
    {$match : {"rating" : 5}},
    {$lookup : {
            from : 'users',
            localField: 'user_id',
            foreignField: '_id',
            as: 'user'
        }
    },
    {$unwind : '$user'},
    {$project : {'zip_code' : '$user.zip_code', "gender":"$user.gender", 'age_group':'$user.age_group' }},
    {$match : {"gender": "F", 'age_group': "56+"}},
    {$project : {'zip_code' : 1, '_id':0 }},
])

db.ratings.aggregate([
    // {$match : {"rating" : 5}},
    {$lookup : {
            from : 'users',
            localField: 'user_id',
            foreignField: '_id',
            as: 'user'
        }
    },
    {$unwind : '$user'},
    {$group : {
            '_id' : {'rating' : '$rating'},
            'value' : {$sum : 1},
        }
    },

    // {$project : {'zip_code' : '$user.zip_code', "gender":"$user.gender", 'age_group':'$user.age_group' }},
    //   {$match : {"gender": "F", 'age_group': "56+"}}
])