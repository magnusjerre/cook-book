import React from 'react';
import PropTypes from 'prop-types';

const RecipeOverview = ({ recipe }) => {
    return (
        <li>
            {
                recipe.name
            }
        </li>
    );
};

RecipeOverview.propTypes = {
    recipe: PropTypes.any.isRequired
};

export default RecipeOverview;